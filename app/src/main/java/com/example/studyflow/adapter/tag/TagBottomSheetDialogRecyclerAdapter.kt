package com.example.studyflow.adapter.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.studyflow.R
import com.example.studyflow.databinding.TagBottomSheetDialogRowBinding
import com.example.studyflow.interfaces.tag.TagBottomSheetDialogClickListener
import com.example.studyflow.model.Tag

class TagBottomSheetDialogRecyclerAdapter(
    private val tagList: ArrayList<Tag>,
    private val listener: TagBottomSheetDialogClickListener
) : RecyclerView.Adapter<TagBottomSheetDialogRecyclerAdapter.TagBottomSheetDialogViewHolder>() {
    class TagBottomSheetDialogViewHolder(var view: TagBottomSheetDialogRowBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TagBottomSheetDialogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TagBottomSheetDialogRowBinding>(
            inflater,
            R.layout.tag_bottom_sheet_dialog_row,
            parent,
            false
        )
        return TagBottomSheetDialogViewHolder(view)

    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: TagBottomSheetDialogViewHolder, position: Int) {
        holder.view.tag = tagList[position]
        holder.view.listener = listener
    }

    fun updateTagList(newTagList: List<Tag>) {
        tagList.clear()
        tagList.addAll(newTagList)
        notifyDataSetChanged()
    }
}