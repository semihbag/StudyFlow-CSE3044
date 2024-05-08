package com.example.studyflow.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyflow.R
import com.example.studyflow.modelview.AddingToDoModelView
import com.example.studyflow.modelview.ToDoFragmentModelView
import java.lang.Exception

class AddingToDoFragment : Fragment() {

    private lateinit var modelView: AddingToDoModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adding_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addButton = view.findViewById<Button>(R.id.addButton)
        modelView = ViewModelProvider(this)[AddingToDoModelView::class.java]
        addButton.setOnClickListener {
            modelView.setPlan(view)
            observe(view)
        }
    }

    private fun observe(view: View) {
        modelView.add_Plan.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isEmpty()) {
                    Toast.makeText(context,"Please enter a plan",Toast.LENGTH_LONG).show()
                }
                else {
                    context?.let { it1 -> modelView.insertPlan(it1) }
                    view.findViewById<EditText>(R.id.toDoPlanText).setText("")
                    modelView.setPlan(view)
                }
            }
        })
    }

}