package com.example.studyflow.adapter.flasmind

import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.databinding.FlashMindTagRowBinding
import com.example.studyflow.model.Tag

class FlashMindTagRecyclerAdapter (private val flashMindTagList : ArrayList<Tag>) : RecyclerView.Adapter<FlashMindTagRecyclerAdapter.FlashMindTagViewHolder>() {

    class FlashMindTagViewHolder(var view : FlashMindTagRowBinding) : RecyclerView.ViewHolder(view.root) {
    }
}