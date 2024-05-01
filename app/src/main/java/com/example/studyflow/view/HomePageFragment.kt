package com.example.studyflow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.studyflow.R




class HomePageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val focusButton = view.findViewById<Button>(R.id.focusButton)
        focusButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToFocusFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val analysisButton = view.findViewById<Button>(R.id.analysisButton)
        analysisButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToAnalysisFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val flashMindButton = view.findViewById<Button>(R.id.flashMindButton)
        flashMindButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToFlashMindFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val planningButton = view.findViewById<Button>(R.id.planningButton)
        planningButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToPlanningFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val toDoButton = view.findViewById<Button>(R.id.todoButton)
        toDoButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToToDoFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val tagsButton = view.findViewById<Button>(R.id.myTagsButton)
        tagsButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToTagsFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}