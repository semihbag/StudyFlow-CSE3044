package com.example.studyflow.view.flashmindview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentExerciseBinding
import com.example.studyflow.interfaces.flashmind.ExerciseFragmentClickListener
import com.example.studyflow.model.Card
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.flashmind.ExerciseViewModel
import java.util.Date

class ExerciseFragment : Fragment(), ExerciseFragmentClickListener {
    private lateinit var viewModel : ExerciseViewModel
    private lateinit var card : Card
    private lateinit var tag : Tag
    private lateinit var binding : FragmentExerciseBinding
    private var markedCardList = ArrayList<Card>()
    private val index = 0
    private var isFront = true
    private var isAnswerSeen = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tag = ExerciseFragmentArgs.fromBundle(it).tag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise, container, false)

        binding.listener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        viewModel.loadMarkedCardsFromDB(tag)

        observeLiveData()


    }

    fun observeLiveData() {

        viewModel.mutableMarkedCardList.observe(viewLifecycleOwner, Observer { markedCards ->
            markedCards.let {
                markedCardList = ArrayList(markedCards)

                if (markedCardList.size == 0) {
                    binding.nothingToShow.visibility = View.VISIBLE
                    binding.scrollView.visibility = View.GONE
                }
                else {
                    binding.nothingToShow.visibility = View.GONE
                    binding.scrollView.visibility = View.VISIBLE
                    this.card = markedCardList.get(index)
                    binding.card = this.card
                }
            }
        })
    }





    private fun flipFrontToBack(duration: Long) {
        val animatorSet = AnimatorSet()

        val flipRotationFront = ObjectAnimator.ofFloat(binding.containerCardFront, "rotationY", 0f, 90f)
        val flipRotationBack = ObjectAnimator.ofFloat(binding.containerCardBack, "rotationY", -90f, 0f)

        val flipAlphaFront = ObjectAnimator.ofFloat(binding.containerCardFront, "alpha", 1f, 0f)
        val flipAlphaBack = ObjectAnimator.ofFloat(binding.containerCardBack, "alpha", 0f, 1f)

        flipRotationFront.duration = duration
        flipRotationBack.duration = duration
        flipAlphaFront.duration = duration
        flipAlphaBack.duration = duration

        animatorSet.playTogether(flipRotationFront, flipRotationBack, flipAlphaFront, flipAlphaBack)
        animatorSet.start()
        isFront = false
    }

    private fun flipBackToFront(duration : Long) {
        val animatorSet = AnimatorSet()

        val flipRotationBack = ObjectAnimator.ofFloat(binding.containerCardBack, "rotationY", 0f, -90f)
        val flipRotationFront = ObjectAnimator.ofFloat(binding.containerCardFront, "rotationY", 90f, 0f)

        val flipAlphaBack = ObjectAnimator.ofFloat(binding.containerCardBack, "alpha", 1f, 0f)
        val flipAlphaFront = ObjectAnimator.ofFloat(binding.containerCardFront, "alpha", 0f, 1f)

        flipRotationBack.duration = duration
        flipRotationFront.duration = duration
        flipAlphaFront.duration = duration
        flipAlphaBack.duration = duration

        animatorSet.playTogether(flipRotationBack, flipRotationFront, flipAlphaFront, flipAlphaBack)
        animatorSet.start()
        isFront = true
    }


    override fun clickBack(view: View) {
        val action = ExerciseFragmentDirections.actionExerciseFragmentToFlashMindFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun clickFalse(view: View) {
        TODO("Not yet implemented")
    }

    override fun clickFlip(view: View) {
        isAnswerSeen = true
        if (isFront) {
            flipFrontToBack(200)
        }
        else {
            flipBackToFront(200)
        }
    }

    override fun clickTrue(view: View) {
        if (isAnswerSeen) {
            // update card
            card.isMarked = false
            card.state = if (card.state == 8) 8 else (card.state + 1)
            card.lastExerciseDate = Date().time
            card.lastAnswer = true
            viewModel.updateCard(tag, card)

            if (!isFront) {
                flipBackToFront(0)
            }

            isAnswerSeen = false
        }
        else {
            Toast.makeText(context, "You haven't even seen the correct answer yet!", Toast.LENGTH_LONG).show()
        }
    }

    override fun clickPass(view: View) {
        TODO("Not yet implemented")
    }

}