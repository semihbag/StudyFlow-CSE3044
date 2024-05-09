package com.example.studyflow.view.pomodoroView

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPomodoroBinding
import com.example.studyflow.databinding.FragmentToDoBinding
import com.example.studyflow.interfaces.pomodoro.PomodoroFragmentClickListener
import com.example.studyflow.viewmodel.BaseViewModel
import com.example.studyflow.viewmodel.pomodoro.PomodoroViewModel
import com.example.studyflow.viewmodel.todo.ToDoViewModel


class PomodoroFragment : Fragment(), PomodoroFragmentClickListener {

    private lateinit var viewModel: PomodoroViewModel
    // UI
    private lateinit var minutes: EditText
    private lateinit var seconds: EditText
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resumeButton: Button
    // counter
    private lateinit var counter: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPomodoroBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pomodoro, container, false
        )

        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PomodoroViewModel::class.java]
        minutes = view.findViewById(R.id.Minutes)
        seconds = view.findViewById(R.id.Seconds)
        startButton = view.findViewById(R.id.startButton)
        stopButton = view.findViewById(R.id.stopButton)
        pauseButton = view.findViewById(R.id.pauseButton)
        resumeButton = view.findViewById(R.id.resmuseButton)

    }

    // click listener functions
    override fun onStart(view: View) {
        // make not editable the text fields
        minutes.isEnabled = false
        seconds.isEnabled = false

        // change the button
        startButton.visibility = View.GONE
        stopButton.visibility = View.VISIBLE

        // create a pomodoro object and start it
        // önce dakika ve saniyeyi atama yapmak lazım sonra
        // ikisi de 00 mı değil mi onun kontorlu lazım
        val minute = minutes.text.toString().toLong()
        val second = seconds.text.toString().toLong()
        if (!(minute == 0L && second == 0L)) {
            viewModel.setMinuteAndSecond(minute, second)
            counter = viewModel.countDownTime(minutes,seconds) // returning counter object
            counter.start()
        }
    }

    override fun onStop(view: View) {

    }

    override fun onPause(view: View) {
        // change the buttons (stop and resume will be visible)
        if (!(!::counter.isInitialized)){
            resumeButton.visibility = View.VISIBLE
            pauseButton.visibility = View.GONE
            // stop the counter
            counter.cancel()
        }
    }

    override fun onResume(view: View) {
        // change the buttons (stop and resume will be visible)
        resumeButton.visibility = View.GONE
        pauseButton.visibility = View.VISIBLE
        // stop the counter
        counter = viewModel.countDownTime(minutes,seconds)
        counter.start()
    }
}