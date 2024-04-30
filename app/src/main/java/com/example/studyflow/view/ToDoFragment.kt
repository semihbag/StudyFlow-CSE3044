package com.example.studyflow.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.studyflow.R
import com.example.studyflow.view.toDoView.CustomAdapterToDo
import com.example.studyflow.view.toDoView.AddingToDoFragment

class ToDoFragment : Fragment() {

    private var plans = ArrayList<String>()
    private var is_checks = ArrayList<Int>()
    private var indices = ArrayList<Int>()
    private lateinit var customAdapter: CustomAdapterToDo
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            val action = ToDoFragmentDirections.actionToDoFragmentToAddingToDoFragment()
            Navigation.findNavController(it).navigate(action)
        }

        collectData()
        customAdapter = CustomAdapterToDo(plans,is_checks,indices)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = customAdapter

    }

    private fun collectData() {

        try {
            context?.let {

                val database = it.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
                val cursor = database.rawQuery("SELECT * FROM toDoPlans",null)
                val planIndex = cursor.getColumnIndex("toDoPlan")
                val checkedIndex = cursor.getColumnIndex("is_checked")
                val indicesIndex = cursor.getColumnIndex("id")

                plans.clear()
                is_checks.clear()
                indices.clear()

                while (cursor.moveToNext()) {
                    plans.add(cursor.getString(planIndex))
                    is_checks.add(cursor.getInt(checkedIndex))
                    indices.add(cursor.getInt(indicesIndex))
                }

                customAdapter.notifyDataSetChanged()

                cursor.close()
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }


}