package com.example.studyflow.view.input_output_edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentEditTextBinding
import com.example.studyflow.enums.EditInputType
import com.example.studyflow.interfaces.input_output_edit.EditTextFragmentClickListener
import com.example.studyflow.model.Card
import com.example.studyflow.model.Tag

class EditTextFragment : Fragment(), EditTextFragmentClickListener {
    private lateinit var binding: FragmentEditTextBinding
    private lateinit var tag : Tag
    private lateinit var card : Card
    private lateinit var editType : EditInputType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.editType = EditTextFragmentArgs.fromBundle(it).editType
            this.tag = EditTextFragmentArgs.fromBundle(it).tag
            EditTextFragmentArgs.fromBundle(it).card?.let { card ->
                this.card = card
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_text, container, false)
        binding.listener = this

        when (editType) {
            EditInputType.CARD_TITLE -> binding.editTextInputLayout.hint = "Enter Card Title"
            EditInputType.TEXT_FRONT -> binding.editTextInputLayout.hint = "Enter Front Text"
            EditInputType.TEXT_BACK -> binding.editTextInputLayout.hint = "Enter Back Text"
            else -> println("Unknown type")
        }

        return binding.root
    }

    override fun clickDone(view: View) {
        when (editType) {
            EditInputType.CARD_TITLE -> card.cardTitle = binding.editTextInput.text.toString()
            EditInputType.TEXT_FRONT -> card.textFront = binding.editTextInput.text.toString()
            EditInputType.TEXT_BACK -> card.textBack = binding.editTextInput.text.toString()
            else -> println("Unknown type")
        }

        val action = EditTextFragmentDirections.actionEditTextFragmentToCardCreateFragment(this.tag, this.card)
        Navigation.findNavController(view).navigate(action)
    }

}