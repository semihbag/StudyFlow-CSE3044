package com.example.studyflow.view.tagview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.tag.TagBottomSheetDialogRecyclerAdapter
import com.example.studyflow.databinding.TagBottomSheetDialogBinding
import com.example.studyflow.databinding.TagBottomSheetDialogRowBinding
import com.example.studyflow.interfaces.tag.TagBottomSheetDialogClickListener
import com.example.studyflow.model.Tag
import com.example.studyflow.view.HomePageFragmentDirections
import com.example.studyflow.viewmodel.tag.TagBottomSheetDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TagBottomSheetDialogFragment() : BottomSheetDialogFragment(), TagBottomSheetDialogClickListener {
    private lateinit var viewModel : TagBottomSheetDialogViewModel
    private lateinit var tagBottomSheetDialogRecyclerAdapter : TagBottomSheetDialogRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TagBottomSheetDialogBinding = DataBindingUtil.inflate(
            inflater, R.layout.tag_bottom_sheet_dialog, container, false
        )
        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TagBottomSheetDialogViewModel::class.java)
        viewModel.loadSelectableTagListFromDB()

        val tagBottomSheetDialogRecyclerView = view.findViewById<RecyclerView>(R.id.tag_bottom_sheet_dialog_recyclerview)
        tagBottomSheetDialogRecyclerView.layoutManager = LinearLayoutManager(context)
        tagBottomSheetDialogRecyclerAdapter = TagBottomSheetDialogRecyclerAdapter(ArrayList<Tag>(),this)
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







    override fun clickAddTagFromTagBottomSheetDialog(view: View) {
        println("tıkla baboli")
        val action =
            TagBottomSheetDialogFragmentDirections.actionTagBottomSheetDialogFragmentToTagsFragment()

        Navigation.findNavController(view).navigate(action)
    }

    override fun clickSelectTag(view: View) {
        val binding = DataBindingUtil.findBinding<TagBottomSheetDialogRowBinding>(view)
        binding?.let {
            it.tag?.let {

                this.dismiss()
            }
        }
    }
}