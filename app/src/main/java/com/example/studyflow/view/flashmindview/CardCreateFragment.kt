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
import com.example.studyflow.interfaces.flashmind.CardCreateBottomSheetClickListener
import com.example.studyflow.interfaces.flashmind.CardCreateClickListener
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.flashmind.CreateCardViewModel

class CardCreateFragment : Fragment(), CardCreateClickListener, CardCreateBottomSheetClickListener {
    private lateinit var viewModel: CreateCardViewModel
    private lateinit var cardCreateBottomSheetDialog: CardCreateBottomSheetDialogFragment
    private lateinit var tag: Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tag = CardCreateFragmentArgs.fromBundle(it).tag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCardCreateBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_card_create, container, false)
        binding.listener = this
        binding.tag = tag

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
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditTextFragment()
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickEditTextFront(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditTextFragment()
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickImageFront(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditImageFragment()
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickEditTextBack(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditTextFragment()
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }

    override fun clickImageBack(v: View) {
        val action = CardCreateFragmentDirections.actionCardCreateFragmentToEditImageFragment()
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        cardCreateBottomSheetDialog.dismiss()
    }
}