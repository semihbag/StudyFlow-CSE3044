package com.example.studyflow.view.pomodoroView

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPomodoroBinding
import com.example.studyflow.databinding.FragmentTagsBinding
import com.example.studyflow.databinding.FragmentToDoBinding
import com.example.studyflow.interfaces.pomodoro.PomodoroFragmentClickListener
import com.example.studyflow.viewmodel.BaseViewModel
import com.example.studyflow.viewmodel.pomodoro.PomodoroViewModel
import com.example.studyflow.viewmodel.todo.ToDoViewModel
import java.util.UUID


class PomodoroFragment : Fragment(), PomodoroFragmentClickListener {

    private lateinit var viewModel: PomodoroViewModel

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
        view.findViewById<TextView>(R.id.InfoText).text = "Pomodoro"
    }

//    private fun observerLiveData(binding: FragmentPomodoroBinding) {
//        viewModel.pomodoroID.observe(viewLifecycleOwner, Observer {
//            println(viewModel.pomodoroId.value.toString().toInt())
//            val args = bundleOf("pomodoroID" to viewModel.pomodoroId.value.toString().toInt() ) // get the tagID and set here
//            binding.root.findNavController().navigate(R.id.action_pomodoroFragment_to_breakFragment, args)
//        })
//    }

    // click listener functions
    override fun onStart(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPomodoroBinding>(view)
        binding?.let{
            // change the text field to not editable
            binding.Minutes.isEnabled = false
            binding.Seconds.isEnabled = false
            // change the buttons (stop will be visible)
            binding.startButton.visibility = View.GONE
            binding.stopButton.visibility = View.VISIBLE


            val minute = binding.Minutes.text.toString().toLong()
            val second = binding.Seconds.text.toString().toLong()

            if (!(minute == 0L && second == 0L)) {
                viewModel.setMinuteAndSecond(minute, second)
                counter = viewModel.countDownTime(binding) // returning counter object
                counter.start()
//                observerLiveData(binding)
            }
        }
    }

    override fun onStop(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPomodoroBinding>(view)
        binding?.let{
            // stop and write database how much time consumed
            counter.cancel()
            viewModel.totalTimeInMilsec.value = viewModel.totalTimeInMilsec.value?.minus(viewModel.remaingTimeInMilsec.value!!)
            counter.onFinish()
            // onFinish handle the button visibility and text editablelity

            binding.Minutes.setText("00")
            binding.Seconds.setText("00")
        }
    }

    override fun onPause(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPomodoroBinding>(view)
        binding?.let{
            if (!(!::counter.isInitialized)){
                binding.resmuseButton.visibility = View.VISIBLE
                binding.pauseButton.visibility = View.GONE
                // stop the counter
                counter.cancel()
            }
        }
        // change the buttons (stop and resume will be visible)

    }

    override fun onResume(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPomodoroBinding>(view)
        binding?.let{
            // change the buttons (stop and resume will be visible)
            binding.resmuseButton.visibility = View.GONE
            binding.pauseButton.visibility = View.VISIBLE
            // stop the counter
            counter = viewModel.countDownTime(binding)
            counter.start()
        }

    }
}