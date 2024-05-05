package com.example.studyflow.adapter.todo

import android.nfc.Tag
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.ToDoSelectTagRowBinding
import com.example.studyflow.interfaces.todo.ToDoFragmentClickListener


class ToDoSelectTagRecyclerAdapter (private val selectTagList : ArrayList<com.example.studyflow.model.Tag>, private  val listenerFragment : ToDoFragmentClickListener) : RecyclerView.Adapter<ToDoSelectTagRecyclerAdapter.ToDoSelectTagViewHolder>() {

    class ToDoSelectTagViewHolder(var view : ToDoSelectTagRowBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoSelectTagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ToDoSelectTagRowBinding>(inflater, R.layout.to_do_select_tag_row, parent,false)
        return ToDoSelectTagViewHolder(view)
    }

    override fun getItemCount(): Int {
        return selectTagList.size
    }

    override fun onBindViewHolder(holder: ToDoSelectTagViewHolder, position: Int) {
        holder.view.tag = selectTagList[position]
        holder.view.listenerFragment = listenerFragment
    }

    fun updateSelectTagList(newSelecTagList : List<com.example.studyflow.model.Tag>) {
        selectTagList.clear()
        selectTagList.addAll(newSelecTagList)
        notifyDataSetChanged()
    }
}