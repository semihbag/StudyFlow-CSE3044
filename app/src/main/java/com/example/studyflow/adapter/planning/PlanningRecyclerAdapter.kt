package com.example.studyflow.adapter.planner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPlanningBinding
import com.example.studyflow.databinding.PlanningRowBinding
import com.example.studyflow.model.Planning

class PlanningRecyclerAdapter(private val planningList: ArrayList<Planning>):
RecyclerView.Adapter<PlanningRecyclerAdapter.PlanningViewHolder>(){
    class PlanningViewHolder(var view: PlanningRowBinding) : RecyclerView.ViewHolder(view.root){
    }
    //hangi rowu hangi tasarımla oluşturacağız söyleyen metot
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        val inflater =LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<PlanningRowBinding>(inflater, R.layout.planning_row,parent,false)
        return PlanningViewHolder(view)
    }

    override fun getItemCount(): Int {
        return planningList.size
    }

    //sayfada bi tıklama işlemi olmayacak silme olacaksa o eklenecek
    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        holder.view.planning =planningList[position]

    }

    fun updatePlanningList(newPlanningList: List<Planning>){
        planningList.clear()
        planningList.addAll(newPlanningList)
        notifyDataSetChanged()
    }

}