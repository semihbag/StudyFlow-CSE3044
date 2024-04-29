package com.example.studyflow

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterToDo(plans: ArrayList<String>,is_checks: ArrayList<Int>, indices: ArrayList<Int>): RecyclerView.Adapter<CustomAdapterToDo.MyViewHolder>() {


    private var plans: ArrayList<String>
    private var is_checks: ArrayList<Int>
    private var indices: ArrayList<Int>

    init {

        this.plans = plans
        this.is_checks = is_checks
        this.indices = indices
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.recycler_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.toDoText.text = plans[position]
        if (is_checks[position] == 0) {
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
                statement.bindString(1,indices[position].toString())
                statement.execute()
            }
            else {
                val sqlString = "UPDATE toDoPlans SET is_checked = 0 WHERE id = ?"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,indices[position].toString())
                statement.execute()
            }
        }

        holder.cardRow.setOnClickListener {
            val action = ToDoFragmentDirections.actionToDoFragmentToDeleteAndUpdatePlanFragment(holder.toDoText.text.toString(),indices[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return plans.size
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