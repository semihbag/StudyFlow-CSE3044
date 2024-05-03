package com.example.studyflow.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.TagRowBinding
import com.example.studyflow.model.Tag

class TagRecyclerAdapter(private val tagList : ArrayList<Tag>) : RecyclerView.Adapter<TagRecyclerAdapter.TagViewHolder>(), TagClickListener {
    // create class
    class TagViewHolder(var view: TagRowBinding) : RecyclerView.ViewHolder(view.root) {
    }

    private var selectedPosition = RecyclerView.NO_POSITION

    // override functions (came from RecyclerView.Adapter)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        // bu databinding olmadan önce kullanılan çağırma yöntemi idi
        //val view = inflater.inflate(R.layout.tag_row,parent,false)
        val view = DataBindingUtil.inflate<TagRowBinding>(inflater, R.layout.tag_row, parent, false)
        return TagViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.view.tag = tagList[position]


        /*
        // calculate the ratio of correct answer
        val numberOfCorrectAnswer = tagList[position].totalNumberOfCurrentCorrectAnswer
        val numberOfTotalCard = tagList[position].totalNumberOfCard
        var progressResult = 0
        if (numberOfTotalCard != 0) {
            progressResult = (numberOfCorrectAnswer.toDouble() / numberOfTotalCard.toDouble() * 100).toInt()
        }
        holder.itemView.findViewById<ProgressBar>(R.id.cardRatioProgressBar).progress = progressResult

        // ratio as a string
        val ratioStr = "$numberOfCorrectAnswer $numberOfTotalCard"
        holder.itemView.findViewById<TextView>(R.id.cardRatio).text = ratioStr

        holder.itemView.setOnClickListener {
            val visibility = holder.itemView.findViewById<androidx.gridlayout.widget.GridLayout>(R.id.gridLayout).visibility
            if (visibility == View.GONE) {
                holder.itemView.findViewById<androidx.gridlayout.widget.GridLayout>(R.id.gridLayout).visibility =View.VISIBLE
            }
            else {
                holder.itemView.findViewById<androidx.gridlayout.widget.GridLayout>(R.id.gridLayout).visibility =View.GONE
            }
        }
        */
    }

    fun updateTagList(newTagList : List<Tag>) {
        tagList.clear()
        tagList.addAll(newTagList)
        notifyDataSetChanged()
    }

    override fun clickTag(view: View) {


    }
}