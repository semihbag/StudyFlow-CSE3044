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
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studyflow.R
import com.example.studyflow.modelview.DeleteAndUpdateModelView
import com.example.studyflow.model.ToDoPlan


class DeleteAndUpdatePlanFragment : Fragment() {

    private lateinit var planText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var  modelView: DeleteAndUpdateModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_and_update_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modelView = ViewModelProvider(this)[DeleteAndUpdateModelView::class.java]
        planText = view.findViewById(R.id.toDoPlanUD)
        updateButton = view.findViewById(R.id.updateButton)
        deleteButton = view.findViewById(R.id.deleteButton)
        deleteButton.visibility = View.VISIBLE
        updateButton.visibility = View.VISIBLE
        var index = -1
        arguments?.let {
            index = DeleteAndUpdatePlanFragmentArgs.fromBundle(it).id
        }
        modelView.initializePlan(index)
        observe(index,"none")

        updateButton.setOnClickListener {
            observe(index,"update")
        }
        deleteButton.setOnClickListener {
            observe(index,"delete")
            deleteButton.visibility = View.INVISIBLE
            updateButton.visibility = View.INVISIBLE
        }
    }

    private fun observe(id: Int, operation: String) {
    modelView.toDoPlan.observe(viewLifecycleOwner, Observer {
        if (operation == "none") {
            planText.setText(it.plan)
        }
        else if (operation == "update") {
            modelView.updatePlan(planText.text.toString(),id)
        }
        else if (operation == "delete") {
            modelView.deletePlan(id)
        }
    })
    }
}
