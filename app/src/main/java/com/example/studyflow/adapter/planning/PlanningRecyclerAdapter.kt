package com.example.studyflow.adapter.planner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPlanningBinding
import com.example.studyflow.model.Planning

class PlanningRecyclerAdapter(private val planningList: ArrayList<Planning>):
RecyclerView.Adapter<PlanningRecyclerAdapter.PlanningViewHolder>(){
    class PlanningViewHolder(var view: FragmentPlanningBinding) : RecyclerView.ViewHolder(view.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        val inflater =LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<FragmentPlanningBinding>(inflater, R.layout.fragment_planning,parent,false)
        return PlanningViewHolder(view)
    }

    override fun getItemCount(): Int {
        return planningList.size
    }

    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        holder.view.planning= planningList[position]
        holder.view.listenerAdapter= this


    }

    fun updatePlanningList(newPlanningList: List<Planning>){
        planningList.clear()
        planningList.addAll(newPlanningList)
        notifyDataSetChanged()
    }

}