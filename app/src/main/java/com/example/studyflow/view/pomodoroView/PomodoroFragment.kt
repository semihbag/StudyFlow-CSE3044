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

    }

    private fun observeLiveData(view: View) {

        if (viewModel.focusingMinutes.value != 0.toLong()) {
            viewModel.focusingMinutes.observe(viewLifecycleOwner, Observer {
                viewModel.countDownTime(view)
            })
        }
        else if(viewModel.focusingSeconds.value != 0.toLong()) {
            viewModel.focusingMinutes.observe(viewLifecycleOwner, Observer {
                viewModel.countDownTime(view)
            })
        }

    }

    // click listener functions
    override fun onStart(view: View) {
        // create a pomodoro object and start it
        // önce dakika ve saniyeyi atama yapmak lazım sonra
        // ikisi de 00 mı değil mi onun kontorlu lazım
        val minutes = view.findViewById<EditText>(R.id.Minutes).toString().toLong()
        val seconds =  view.findViewById<EditText>(R.id.Seconds).toString().toLong()
        if (!(minutes == 0.toLong() && seconds == 0.toLong())) {
            viewModel.setMinuteAndSecond(minutes,seconds)
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