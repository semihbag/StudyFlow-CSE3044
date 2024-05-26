package com.example.studyflow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentHomePageBinding
import com.example.studyflow.interfaces.home.HomePageFragmentClickListener


class HomePageFragment : Fragment(), HomePageFragmentClickListener{
    private lateinit var binding: FragmentHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_page, container, false
        )
        binding.listener = this

        binding.textFocus.isSelected = true
        binding.textFlashMind.isSelected = true
        binding.textAnalysis.isSelected = true
        binding.textPlaning.isSelected = true
        binding.textToDo.isSelected = true
        binding.textTags.isSelected = true

        return binding.root
    }



    override fun clickGoFocusPage(view: View) {
        val action = HomePageFragmentDirections.actionHomePageFragmentToFocusFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun clickGoFlashMindPage(view: View) {
        val action = HomePageFragmentDirections.actionHomePageFragmentToFlashMindFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun clickGoAnalysisPage(view: View) {
        val action = HomePageFragmentDirections.actionHomePageFragmentToAnalysisFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun clickGoPlanningPage(view: View) {
        val action = HomePageFragmentDirections.actionHomePageFragmentToPlanningFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun clickGoTodoPage(view: View) {
        val action = HomePageFragmentDirections.actionHomePageFragmentToToDoFragment(null)
        Navigation.findNavController(view).navigate(action)
    }

    override fun clickGoTagsPage(view: View) {
        val action = HomePageFragmentDirections.actionHomePageFragmentToTagsFragment()
        Navigation.findNavController(view).navigate(action)
    }
}