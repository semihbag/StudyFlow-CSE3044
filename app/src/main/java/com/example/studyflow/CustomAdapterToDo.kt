package com.example.studyflow

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterToDo(activity: Activity, context: Context, messages: ArrayList<String>): RecyclerView.Adapter<CustomAdapterToDo.MyViewHolder>() {

    private var activity: Activity
    private var context: Context
    private var messages: ArrayList<String>

    init {
        this.activity = activity
        this.context = context
        this.messages = messages
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.recycler_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.messageText.text = messages[position]
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var messageText: TextView
        var checkBox: CheckBox
        var cardRow: ConstraintLayout

        init {
            messageText = itemView.findViewById(R.id.toDoText)
            checkBox = itemView.findViewById(R.id.checkBox)
            cardRow = itemView.findViewById(R.id.rowCard)
        }
    }
}