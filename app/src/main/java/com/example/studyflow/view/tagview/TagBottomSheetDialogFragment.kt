package com.example.studyflow.view.tagview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.tag.TagBottomSheetDialogRecyclerAdapter
import com.example.studyflow.databinding.TagBottomSheetDialogBinding
import com.example.studyflow.databinding.TagBottomSheetDialogRowBinding
import com.example.studyflow.interfaces.tag.TagBottomSheetDialogClickListener
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.tag.TagBottomSheetDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TagBottomSheetDialogFragment(private val listenerTagBottomSheetDialog : TagBottomSheetDialogClickListener) : BottomSheetDialogFragment()  {
    private lateinit var viewModel : TagBottomSheetDialogViewModel
    private lateinit var tagBottomSheetDialogRecyclerAdapter : TagBottomSheetDialogRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TagBottomSheetDialogBinding = DataBindingUtil.inflate(
            inflater, R.layout.tag_bottom_sheet_dialog, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TagBottomSheetDialogViewModel::class.java)
        viewModel.loadSelectableTagListFromDB()

        val tagBottomSheetDialogRecyclerView = view.findViewById<RecyclerView>(R.id.tag_bottom_sheet_dialog_recyclerview)
        tagBottomSheetDialogRecyclerView.layoutManager = LinearLayoutManager(context)
        tagBottomSheetDialogRecyclerAdapter = TagBottomSheetDialogRecyclerAdapter(ArrayList<Tag>(),listenerTagBottomSheetDialog)
        tagBottomSheetDialogRecyclerView.adapter = tagBottomSheetDialogRecyclerAdapter

        observeLiveData()
    }

   fun observeLiveData() {
       viewModel.mutableSelectableTagList.observe(viewLifecycleOwner, Observer { tags ->
           tags.let {
               view?.findViewById<RecyclerView>(R.id.tag_bottom_sheet_dialog_recyclerview)?.visibility = View.VISIBLE
               tagBottomSheetDialogRecyclerAdapter.updateTagList(ArrayList(tags))
           }
       })
   }





}