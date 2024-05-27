package com.example.studyflow.view.flashmindview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentExerciseBinding
import com.example.studyflow.interfaces.flashmind.ExerciseFragmentClickListener
import com.example.studyflow.model.Card
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.flashmind.ExerciseViewModel

class ExerciseFragment : Fragment(), ExerciseFragmentClickListener {
    private lateinit var viewModel : ExerciseViewModel
    private lateinit var card : Card
    private lateinit var tag : Tag
    private lateinit var binding : FragmentExerciseBinding
    private var markedCardList = ArrayList<Card>()
    private val index = 0

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

        if (markedCardList.size == 0) {
            binding.nothingToShow.visibility = View.VISIBLE
            binding.scrollView.visibility = View.GONE
        }
        else {
            binding.nothingToShow.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE
            this.card = markedCardList.get(index)
        }
    }

    fun observeLiveData() {

        viewModel.mutableMarkedCardList.observe(viewLifecycleOwner, Observer { markedCards ->
            markedCards.let {
                markedCardList = ArrayList(markedCards)
            }
        })
    }











    override fun clickBack(view: View) {
        TODO("Not yet implemented")
    }

    override fun clickFalse(view: View) {
        TODO("Not yet implemented")
    }

    override fun clickFlip(view: View) {
        TODO("Not yet implemented")
    }

    override fun clickTrue(view: View) {
        TODO("Not yet implemented")
    }

    override fun clickPass(view: View) {
        TODO("Not yet implemented")
    }


}