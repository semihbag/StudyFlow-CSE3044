package com.example.studyflow.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.model.Tag

class TagRecyclerAdapter(private val tagList : ArrayList<Tag>) : RecyclerView.Adapter<TagRecyclerAdapter.TagViewHolder>() {
    // create class
    class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    private var selectedPosition = RecyclerView.NO_POSITION

    // override functions (came from RecyclerView.Adapter)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.tag_row,parent,false)
        return TagViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tagTittle).text = tagList[position].tagName
        holder.itemView.findViewById<TextView>(R.id.totalPomodoro).text = tagList[position].totalNumberOfPomodoro.toString()
        holder.itemView.findViewById<TextView>(R.id.totalCard).text = tagList[position].totalNumberOfCard.toString()
        holder.itemView.findViewById<TextView>(R.id.totalFocused).text = tagList[position].totalNumberOfOutOfFocusedMinute.toString()
        holder.itemView.findViewById<TextView>(R.id.totalOutOfFocused).text = tagList[position].totalNumberOfOutOfFocusedMinute.toString()
        holder.itemView.findViewById<TextView>(R.id.totalStop).text = tagList[position].totalNumberOfStop.toString()

        // calculate the ratio of correct answer
        val numberOfCorrectAnswer = tagList[position].totalNumberOfCurrentCorrectAnswer
        val numberOfTotalCard = tagList[position].totalNumberOfCard
        val progressResult = (numberOfCorrectAnswer / numberOfTotalCard) * 100
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
    }

    fun updateTagList(newTagList : List<Tag>) {
        tagList.clear()
        tagList.addAll(newTagList)
        notifyDataSetChanged()
    }
}