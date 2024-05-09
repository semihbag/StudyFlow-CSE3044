package com.example.studyflow.view.pomodoroView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var minutes: EditText
    private lateinit var seconds: EditText

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
        observeLiveData(view)

    }

    private fun observeLiveData(view: View) {
        if (viewModel.focusingMinutes.value != null && viewModel.focusingMinutes.value != 0L) {
            viewModel.focusingMinutes.observe(viewLifecycleOwner, Observer {
                viewModel.countDownTime(view)
            })
        }
        else if(viewModel.focusingSeconds.value != null && viewModel.focusingSeconds.value != 0L) {
            viewModel.focusingSeconds.observe(viewLifecycleOwner, Observer {
                viewModel.countDownTime(view)
            })
        }

    }

    // click listener functions
    override fun onStart(view: View) {
        // make not editable the text fields
        minutes.isEnabled = false
        seconds.isEnabled = false

        // create a pomodoro object and start it
        // önce dakika ve saniyeyi atama yapmak lazım sonra
        // ikisi de 00 mı değil mi onun kontorlu lazım
        val minutes = minutes.text.toString().toLong()
        val seconds = seconds.text.toString().toLong()
        if (!(minutes == 0L && seconds == 0L)) {
            viewModel.setMinuteAndSecond(minutes, seconds)
            observeLiveData(view)
        }
    }

    override fun onStop(view: View) {

    }


    override fun onPause(view: View) {
    }

    override fun onResume(view: View) {
    }
}