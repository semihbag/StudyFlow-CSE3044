package com.example.studyflow.view.flashmindview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentCardCreateBinding
import com.example.studyflow.enums.EditInputType
import com.example.studyflow.interfaces.flashmind.CardCreateBottomSheetClickListener
import com.example.studyflow.interfaces.flashmind.CardCreateClickListener
import com.example.studyflow.model.Card
import com.example.studyflow.model.Tag
import com.example.studyflow.util.convertStringToUri
import com.example.studyflow.viewmodel.flashmind.CreateCardViewModel
import java.util.Date

class CardCreateFragment : Fragment(), CardCreateClickListener, CardCreateBottomSheetClickListener {
    private lateinit var viewModel: CreateCardViewModel
    private lateinit var cardCreateBottomSheetDialog: CardCreateBottomSheetDialogFragment
    private lateinit var card : Card
    private lateinit var tag: Tag
    private lateinit var binding: FragmentCardCreateBinding
    private var isFrontVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { it ->
            tag = CardCreateFragmentArgs.fromBundle(it).tag
            CardCreateFragmentArgs.fromBundle(it).card?.let {card ->
                this.card = card
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_create, container, false)

        binding.listener = this
        binding.tag = tag

        if (::card.isInitialized) {
            binding.card = card
        }
        else {
            this.card = Card(
                "",
                tag.uuid,
                0,
                "",
                "",
                "",
                "",
                true,
                1,
                0,
                false,
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CreateCardViewModel::class.java)

        cardCreateBottomSheetDialog = CardCreateBottomSheetDialogFragment(this)

        binding.imageViewCardFront.convertStringToUri(card.imagePathFront)
        binding.imageViewCardBack.convertStringToUri(card.imagePathBack)


        val containerCardFront = binding.containerCardFront
        val containerCardBack = binding.containerCardBack

        containerCardFront.setOnClickListener {
            val animatorSet = AnimatorSet()

            val flipRotationFront = ObjectAnimator.ofFloat(containerCardFront, "rotationY", 0f, 90f)
            val flipRotationBack = ObjectAnimator.ofFloat(containerCardBack, "rotationY", -90f, 0f)

            val flipAlphaFront = ObjectAnimator.ofFloat(containerCardFront, "alpha", 1f, 0f)
            val flipAlphaBack = ObjectAnimator.ofFloat(containerCardBack, "alpha", 0f, 1f)

            flipRotationFront.duration = 200
            flipRotationBack.duration = 200
            flipAlphaFront.duration = 200
            flipAlphaBack.duration = 200

            animatorSet.playTogether(flipRotationFront, flipRotationBack, flipAlphaFront, flipAlphaBack)
            animatorSet.start()
        }

        containerCardBack.setOnClickListener {
            val animatorSet = AnimatorSet()

            val flipRotationBack = ObjectAnimator.ofFloat(containerCardBack, "rotationY", 0f, -90f)
            val flipRotationFront = ObjectAnimator.ofFloat(containerCardFront, "rotationY", 90f, 0f)

            val flipAlphaBack = ObjectAnimator.ofFloat(containerCardBack, "alpha", 1f, 0f)
            val flipAlphaFront = ObjectAnimator.ofFloat(containerCardFront, "alpha", 0f, 1f)

            flipRotationBack.duration = 200
            flipRotationFront.duration = 200
            flipAlphaFront.duration = 200
            flipAlphaBack.duration = 200

            animatorSet.playTogether(flipRotationBack, flipRotationFront, flipAlphaFront, flipAlphaBack)
            animatorSet.start()
        }


    }


    // FUNCTION OF CLICK LISTENER OF CARD CREATE FRAGMENT
    override fun clickEditCard(view: View) {
        cardCreateBottomSheetDialog.show(requireActivity().supportFragmentManager, "b")
    }

    override fun clickCreateCard(view: View) {

        if (card.cardTitle.isNullOrEmpty()) {
            Toast.makeText(context, "Card Title Can Not Be Empty!", Toast.LENGTH_SHORT).show()
            return
        }

        if (card.textFront.isNullOrEmpty() && card.imagePathFront.isNullOrEmpty()) {
            Toast.makeText(context, "Front Side Can Not Be Empty!", Toast.LENGTH_SHORT).show()
            return
        }

        if (card.textBack.isNullOrEmpty() && card.imagePathBack.isNullOrEmpty()) {
            Toast.makeText(context, "Back Side Can Not Be Empty!", Toast.LENGTH_SHORT).show()
            return
        }

        val currentDate = Date().time
        card.createDate = currentDate
        card.lastExerciseDate = currentDate

    }



    // FUNCTION OF CLICK LISTENER OF CARD CREATE BOTTOM SHEET DIALOG
    // buradaki parametre olarak gelen viewları kullanmaya gerek yok ama onu paraemter olarak yazmazsak da fonk çalışmaz
    override fun clickCardTitle(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditTextFragment(this.tag, this.card, EditInputType.CARD_TITLE)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickEditTextFront(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditTextFragment(this.tag, this.card, EditInputType.TEXT_FRONT)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickImageFront(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditImageFragment(this.tag, this.card, EditInputType.IMAGE_FRONT)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickEditTextBack(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditTextFragment(this.tag, this.card, EditInputType.TEXT_BACK)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickImageBack(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditImageFragment(this.tag, this.card, EditInputType.IMAGE_BACK)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }
}