package com.example.studyflow.adapter.planning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.studyflow.R
import com.example.studyflow.databinding.PlanningRowBinding
import com.example.studyflow.model.Plan

class PlanningRecyclerAdapter(private val planList: ArrayList<Plan>) : RecyclerView.Adapter<PlanningRecyclerAdapter.PlanViewHolder>() {

    class PlanViewHolder(var view : PlanningRowBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<PlanningRowBinding>(inflater, R.layout.planning_row,parent,false)
        return PlanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return planList.size
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.view.plan = planList[position]
        holder.view.textPlaning.isSelected = true
    }

    fun updatePlanList(newList : List<Plan>) {
        planList.clear()
        planList.addAll(newList)
        notifyDataSetChanged()
    }
}