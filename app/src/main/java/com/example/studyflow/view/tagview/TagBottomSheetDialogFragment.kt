package com.example.studyflow.view.tagview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.tag.TagBottomSheetDialogRecyclerAdapter
import com.example.studyflow.databinding.TagBottomSheetDialogBinding
import com.example.studyflow.model.Tag
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TagBottomSheetDialogFragment (): BottomSheetDialogFragment() {
    val tagBottomSheetDialogRecyclerAdapter = TagBottomSheetDialogRecyclerAdapter(ArrayList<Tag>())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : TagBottomSheetDialogBinding = DataBindingUtil.inflate(
            inflater, R.layout.tag_bottom_sheet_dialog, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tagBottomSheetDialogRecyclerView = view.findViewById<RecyclerView>(R.id.tag_bottom_sheet_dialog_recyclerview)
        tagBottomSheetDialogRecyclerView.layoutManager = LinearLayoutManager(context)
        tagBottomSheetDialogRecyclerView.adapter = tagBottomSheetDialogRecyclerAdapter
    }

    fun updateAdapterList(newTagList : List<Tag>) {
        tagBottomSheetDialogRecyclerAdapter.updateTagList(newTagList)
    }
}