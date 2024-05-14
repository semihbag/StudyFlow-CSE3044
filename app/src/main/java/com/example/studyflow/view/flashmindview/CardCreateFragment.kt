package com.example.studyflow.view.flashmindview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.studyflow.viewmodel.flashmind.CreateCardViewModel

class CardCreateFragment : Fragment(), CardCreateClickListener, CardCreateBottomSheetClickListener {
    private lateinit var viewModel: CreateCardViewModel
    private lateinit var cardCreateBottomSheetDialog: CardCreateBottomSheetDialogFragment
    private lateinit var card : Card
    private lateinit var tag: Tag


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
        val binding: FragmentCardCreateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_create, container, false)

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

    }


    // FUNCTION OF CLICK LISTENER OF CARD CREATE FRAGMENT
    override fun clickEditCard(view: View) {
        cardCreateBottomSheetDialog.show(requireActivity().supportFragmentManager, "b")
    }

    override fun clickCreateCard(view: View) {
        println("dur hele daha yazmadım")
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