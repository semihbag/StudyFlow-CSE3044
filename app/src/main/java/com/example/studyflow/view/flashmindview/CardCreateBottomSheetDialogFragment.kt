package com.example.studyflow.view.flashmindview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.studyflow.R
import com.example.studyflow.databinding.CreateCardBottomSheetDialogBinding
import com.example.studyflow.interfaces.flashmind.CardCreateBottomSheetClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CardCreateBottomSheetDialogFragment(private val listener : CardCreateBottomSheetClickListener) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // burada binding class ismi ters evet sonradan fark etim ama değiştirmeye üşendim
        val binding : CreateCardBottomSheetDialogBinding = DataBindingUtil.inflate(inflater, R.layout.create_card_bottom_sheet_dialog, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listener = listener
        return binding.root
    }
}