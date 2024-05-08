package com.example.studyflow.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.CustomAdapterToDo
import com.example.studyflow.model.ToDoPlan
import com.example.studyflow.modelview.ToDoFragmentModelView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoFragment : Fragment() {

    private lateinit var modelView: ToDoFragmentModelView
    private  var toDoAdapter =  CustomAdapterToDo(ArrayList<ToDoPlan>())

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
            val action = com.example.studyflow.view.ToDoFragmentDirections.actionToDoFragmentToAddingToDoFragment3()
            Navigation.findNavController(it).navigate(action)
        }

        modelView = ViewModelProvider(this)[ToDoFragmentModelView::class.java]
        modelView.initializePlansFromDB(view.context)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = toDoAdapter

        observe()
    }

    private fun observe() {

        modelView.plans.observe(viewLifecycleOwner, Observer {
            it?.let {
                toDoAdapter.updateList(it)
            }
        })

    }


}