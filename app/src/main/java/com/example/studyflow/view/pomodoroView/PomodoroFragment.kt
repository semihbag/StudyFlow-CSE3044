package com.example.studyflow.view.pomodoroView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyflow.R
import com.example.studyflow.interfaces.pomodoro.PomodoroFragmentClickListener
import com.example.studyflow.viewmodel.BaseViewModel
import com.example.studyflow.viewmodel.pomodoro.PomodoroViewModel


class PomodoroFragment : Fragment(), PomodoroFragmentClickListener {
    private lateinit var viewModel: PomodoroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pomodoro, container, false)
    }

    // click listener functions
    override fun onStart(view: View) {
        // create a pomodoro object and start it
    }

    override fun onStop(view: View) {
    }

    override fun onPause(view: View) {
    }

    override fun onResume(view: View) {
    }
}