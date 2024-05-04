package com.example.studyflow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.model.ToDoPlan
import com.example.studyflow.view.ToDoFragmentDirections

class CustomAdapterToDo(plans: ArrayList<ToDoPlan>): RecyclerView.Adapter<CustomAdapterToDo.MyViewHolder>() {


    private var plans: ArrayList<ToDoPlan>

    init {
        this.plans = plans
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.recycler_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.toDoText.text = plans[position].plan
        if (plans[position].is_check == 0) {
            holder.checkBox.isChecked = false
        }
        else {
            holder.checkBox.isChecked = true
        }

        holder.checkBox.setOnClickListener {

            val database = it.context.openOrCreateDatabase("StudyFlow",Context.MODE_PRIVATE,null)
            if (holder.checkBox.isChecked) {
                val sqlString = "UPDATE toDoPlans SET is_checked = 1 WHERE id = ?"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,plans[position].id.toString())
                statement.execute()
            }
            else {
                val sqlString = "UPDATE toDoPlans SET is_checked = 0 WHERE id = ?"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,plans[position].id.toString())
                statement.execute()
            }
        }

        holder.cardRow.setOnClickListener {
            val action = com.example.studyflow.view.ToDoFragmentDirections.actionToDoFragmentToDeleteAndUpdatePlanFragment5(plans[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return plans.size
    }

    fun updateList(newPlans: List<ToDoPlan> ) {
        plans.clear()
        plans.addAll(newPlans)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var toDoText: TextView
        var checkBox: CheckBox
        var cardRow: ConstraintLayout

        init {
            toDoText = itemView.findViewById(R.id.toDoText)
            checkBox = itemView.findViewById(R.id.checkBox)
            cardRow = itemView.findViewById(R.id.rowCard)

        }
    }
}