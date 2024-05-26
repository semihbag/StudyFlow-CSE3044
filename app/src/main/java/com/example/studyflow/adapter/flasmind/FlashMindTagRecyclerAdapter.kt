package com.example.studyflow.adapter.flasmind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.FlashMindTagRowBinding
import com.example.studyflow.model.Tag

class FlashMindTagRecyclerAdapter (private val flashMindTagList : ArrayList<Tag>) : RecyclerView.Adapter<FlashMindTagRecyclerAdapter.FlashMindTagViewHolder>() {

    class FlashMindTagViewHolder(var view : FlashMindTagRowBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashMindTagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FlashMindTagRowBinding>(inflater, R.layout.flash_mind_tag_row, parent,false)
        return  FlashMindTagViewHolder(view)
    }

    override fun getItemCount(): Int {
        return flashMindTagList.size
    }

    override fun onBindViewHolder(holder: FlashMindTagViewHolder, position: Int) {
        holder.view.tag = flashMindTagList[position]
        holder.view.tagName.isSelected = true
    }

    fun updateFlashMindTagList(newList : List<Tag>) {
        flashMindTagList.clear()
        flashMindTagList.addAll(newList)
        notifyDataSetChanged()
    }
}