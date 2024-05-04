package com.example.studyflow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.RecyclerRowBinding
import com.example.studyflow.model.ToDoPlan
import com.example.studyflow.view.ToDoFragmentDirections

class CustomAdapterToDo(plans: ArrayList<ToDoPlan>): RecyclerView.Adapter<CustomAdapterToDo.MyViewHolder>(), RecyclerPlanClickListener {


    private var plans: ArrayList<ToDoPlan>

    init {
        this.plans = plans
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
     // val view: View = inflater.inflate(R.layout.recycler_row,parent,false)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(inflater,R.layout.recycler_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.view.plan = plans[position]
        holder.view.listener = this

        if (holder.view.plan.is_check == 0) {
            holder.checkBox.isChecked = false
        }
        else {
            holder.checkBox.isChecked = true
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

    class MyViewHolder(var view: RecyclerRowBinding): RecyclerView.ViewHolder(view.root) {

        var toDoText: TextView
        var checkBox: CheckBox
        var cardRow: ConstraintLayout

        init {
            toDoText = itemView.findViewById(R.id.toDoText)
            checkBox = itemView.findViewById(R.id.checkBox)
            cardRow = itemView.findViewById(R.id.rowCard)

        }
    }

    override fun rowClick(view: View) {
        val action = com.example.studyflow.view.ToDoFragmentDirections.actionToDoFragmentToDeleteAndUpdatePlanFragment5(view.findViewById<TextView>(R.id.secretPlanId).text.toString().toInt())
        Navigation.findNavController(view).navigate(action)
    }

    override fun checkBoxClick(view: View) {
        val database = view.context.openOrCreateDatabase("StudyFlow",Context.MODE_PRIVATE,null)
        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
            val sqlString = "UPDATE toDoPlans SET is_checked = 1 WHERE id = ?"
            val statement = database.compileStatement(sqlString)
            statement.bindString(1,view.findViewById<TextView>(R.id.secretPlanId).text.toString())
            statement.execute()
        }
        else {
            val sqlString = "UPDATE toDoPlans SET is_checked = 0 WHERE id = ?"
            val statement = database.compileStatement(sqlString)
            statement.bindString(1,view.findViewById<TextView>(R.id.secretPlanId).text.toString())
            statement.execute()
        }
    }
}