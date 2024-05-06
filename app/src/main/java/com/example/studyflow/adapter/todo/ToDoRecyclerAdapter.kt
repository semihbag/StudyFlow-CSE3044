package com.example.studyflow.adapter.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.ToDoRowBinding
import com.example.studyflow.model.ToDo

/*
    BU İÇERİDEKİ RECYCLER ADAPTER OLACAK
 */
class ToDoRecyclerAdapter(private val toDoList: ArrayList<ToDo>) : RecyclerView.Adapter<ToDoRecyclerAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(var view : ToDoRowBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ToDoRowBinding>(inflater, R.layout.to_do_row, parent, false)
        return  ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.view.toDo = toDoList[position]
    }

    fun updateToDoList(newToDoList : List<ToDo>) {
        toDoList.clear()
        toDoList.addAll(newToDoList)
        notifyDataSetChanged()
    }
}