package com.example.studyflow.view.planningview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.findFragment
import com.example.studyflow.R

class PlanningDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View= inflater.inflate(R.layout.planning_dialog, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txt= view.findViewById<TextView>(R.id.txtAddedPlanning)
        val button= view.findViewById<Button>(R.id.saveAddedPlanning)
        txt.setOnClickListener {

        }
        button.setOnClickListener {

        }


    }

}