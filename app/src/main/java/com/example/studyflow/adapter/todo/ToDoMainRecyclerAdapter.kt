package com.example.studyflow.adapter.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.ToDoMainRecyclerRowBinding
import com.example.studyflow.interfaces.todo.ToDoFragmentClickListener
import com.example.studyflow.model.ToDoMainRecyclerItem

class ToDoMainRecyclerAdapter(
    private val toDoMainRecyclerItemList: ArrayList<ToDoMainRecyclerItem>,
    private val context: Context,
    private val toDoFragmentClickListener: ToDoFragmentClickListener
) :
    RecyclerView.Adapter<ToDoMainRecyclerAdapter.ToDoMainRecyclerItemViewHolder>() {
    class ToDoMainRecyclerItemViewHolder(var view: ToDoMainRecyclerRowBinding) :
        RecyclerView.ViewHolder(view.root) {
    }

    private val innerAdapterList = ArrayList<ToDoRecyclerAdapter>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToDoMainRecyclerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ToDoMainRecyclerRowBinding>(
            inflater,
            R.layout.to_do_main_recycler_row,
            parent,
            false
        )
        return ToDoMainRecyclerItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return toDoMainRecyclerItemList.size
    }

    override fun onBindViewHolder(holder: ToDoMainRecyclerItemViewHolder, position: Int) {

        holder.view.mainItem = toDoMainRecyclerItemList[position]
        holder.view.tagTitle.isSelected = true

        holder.view.innerToToRecyclerview.layoutManager = LinearLayoutManager(context)

        val innerAdapter = ToDoRecyclerAdapter(toDoMainRecyclerItemList[position].toDoList,toDoFragmentClickListener)
        holder.view.innerToToRecyclerview.adapter = innerAdapter

        //  innerAdapter.updateToDoList(toDoMainRecyclerItemList[position].toDoList)

        innerAdapterList.add(innerAdapter)


    }

    fun updateToDoMainRecyclerItemList(newlist: ArrayList<ToDoMainRecyclerItem>) {
        toDoMainRecyclerItemList.clear()
        toDoMainRecyclerItemList.addAll(newlist)

        innerAdapterList.clear()

        for (item in newlist) {
            val innerAdapter = ToDoRecyclerAdapter(item.toDoList, toDoFragmentClickListener)
            innerAdapterList.add(innerAdapter)
        }

        notifyDataSetChanged()
    }

}