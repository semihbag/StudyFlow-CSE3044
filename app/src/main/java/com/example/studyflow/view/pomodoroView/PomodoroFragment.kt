package com.example.studyflow.view.pomodoroView

import android.nfc.Tag
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPomodoroBinding
import com.example.studyflow.databinding.FragmentTagsBinding
import com.example.studyflow.databinding.FragmentToDoBinding
import com.example.studyflow.databinding.TagBottomSheetDialogRowBinding
import com.example.studyflow.interfaces.pomodoro.PomodoroFragmentClickListener
import com.example.studyflow.interfaces.tag.TagBottomSheetDialogClickListener
import com.example.studyflow.interfaces.tag.TagFragmentClickListener
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.BaseViewModel
import com.example.studyflow.viewmodel.pomodoro.PomodoroViewModel
import com.example.studyflow.viewmodel.todo.ToDoViewModel
import java.util.Calendar
import java.util.UUID


class PomodoroFragment : Fragment(), PomodoroFragmentClickListener, TagBottomSheetDialogClickListener {

    private lateinit var viewModel: PomodoroViewModel
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment

    // pomodoro variables
    private var focusingMinutes: Long = 0
    private var focusingSeconds: Long = 0
    private var calendarStart : Calendar? = null
    private var calendarEnd : Calendar? = null
    private var pomodoroID: Int? = null


    // counter
    private lateinit var counter: CountDownTimer
    private var totalTimeInMilsec: Long = 0 // Çalıştığı toplam süre
    private var remaingTimeInMilsec: Long = 0
    private var enteredTimeInMilsec: Long = 0  // İlk başta kaç girdi

    // tag
    private lateinit var selectedTag: com.example.studyflow.model.Tag
    private var tagID = -1

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

        tagBottomSheetDialogFragment = TagBottomSheetDialogFragment(this)
    }


    fun observeLiveData() {
        viewModel.pomodoroID.observe(viewLifecycleOwner, Observer {
            this.pomodoroID = it
        })
    }

    private fun setMinuteAndSecond(minutes: Long, seconds: Long) {

        focusingMinutes = minutes
        focusingSeconds = seconds
        totalTimeInMilsec = (minutes * 60000) + (seconds * 1000)
        remaingTimeInMilsec = totalTimeInMilsec
        enteredTimeInMilsec = totalTimeInMilsec

    }

    // InActiveTıme = EndTime - StartTime milisaniye türünde
    private fun calculateInActiveTime(): Long {
        val minToSec = (calendarEnd!!.get(Calendar.MINUTE) - calendarStart!!.get(Calendar.MINUTE)).times(60)
        val secDif = calendarEnd!!.get(Calendar.SECOND) - calendarStart!!.get(Calendar.SECOND)
        // farkı milisaniye olarak depoluyorum
        println(minToSec)
        println(secDif)
        println(minToSec + secDif)
        println(totalTimeInMilsec)
        return  ((minToSec + secDif).times(1000) - totalTimeInMilsec)
    }

    private fun countDownTime(binding: FragmentPomodoroBinding, tagID: Int): CountDownTimer {
        // burada da database tablosundaki start columnu için Calendar objesi oluşturuluyor ilk kez
        if (calendarStart == null) {
            // Anlık zamanı milisaniye türünde alma
            val calendarObject = Calendar.getInstance()
            calendarObject.timeInMillis = System.currentTimeMillis()
            calendarStart = calendarObject

        }

        val counter =  object : CountDownTimer(remaingTimeInMilsec, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                remaingTimeInMilsec = remaingTimeInMilsec.minus(1000L)
                if ( (millisUntilFinished / 60000) < 10) {
                    binding.Minutes.setText("0" + (millisUntilFinished / 60000).toString())
                }
                else {
                    binding.Minutes.setText((millisUntilFinished / 60000).toString())
                }
                if ( ((millisUntilFinished % 60000) / 1000) < 10) {
                    binding.Seconds.setText("0" + ((millisUntilFinished % 60000) / 1000).toString())
                }
                else {
                    binding.Seconds.setText(((millisUntilFinished % 60000) / 1000).toString())
                }
            }

            override fun onFinish() {
                // Toplam süre bittiğinde de obje initialize edilip
                // focus kısma geçilmeli
                val calendarObject = Calendar.getInstance()
                calendarObject.timeInMillis = System.currentTimeMillis()
                calendarEnd = calendarObject
                if (calendarStart != null && calendarEnd != null) {
                    if (pomodoroID == null) {
                        var pomodoro: Pomodoro? = null
                        if (enteredTimeInMilsec == totalTimeInMilsec) {
                            pomodoro = Pomodoro(calendarStart!!.timeInMillis, calendarEnd!!.timeInMillis,enteredTimeInMilsec,totalTimeInMilsec,0,calculateInActiveTime(),tagID,1)
                        }
                        else {
                            pomodoro = Pomodoro(calendarStart!!.timeInMillis, calendarEnd!!.timeInMillis,enteredTimeInMilsec,totalTimeInMilsec,0,calculateInActiveTime(),tagID,0)
                        }
                        viewModel.insertPomodoro(pomodoro)
                        pomodoroID = viewModel.pomodoroID.value
                        observeLiveData()
                        binding.InfoText.setText("Break")
                    }
                    else {
                        viewModel.updatePomodoro(totalTimeInMilsec, pomodoroID!!)
                        binding.InfoText.setText("Pomodoro")
                        pomodoroID = null
                    }
                }

                // make editable editText
                binding.Minutes.isEnabled = true
                binding.Seconds.isEnabled = true
                binding.startButton.visibility = View.VISIBLE
                binding.stopButton.visibility = View.GONE
                binding.pauseButton.visibility = View.VISIBLE
                binding.resmuseButton.visibility = View.GONE
            }
        }
        return counter
    }


    // click listener functions
    override fun onStart(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPomodoroBinding>(view)
        binding?.let{
            val minute = binding.Minutes.text.toString().toLong()
            val second = binding.Seconds.text.toString().toLong()

            if (!(minute == 0L && second == 0L)) {
                // change the text field to not editable
                binding.Minutes.isEnabled = false
                binding.Seconds.isEnabled = false
                // change the buttons (stop will be visible)
                binding.startButton.visibility = View.GONE
                binding.stopButton.visibility = View.VISIBLE
                setMinuteAndSecond(minute, second)
                counter = countDownTime(binding, tagID) // returning counter object
                counter.start()
            }

            if (::selectedTag.isInitialized){
                binding.tag = selectedTag
            }
        }
    }

    override fun onStop(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPomodoroBinding>(view)
        binding?.let{
            // stop and write database how much time consumed
            counter.cancel()
            totalTimeInMilsec = totalTimeInMilsec.minus(remaingTimeInMilsec)
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
            counter = countDownTime(binding, tagID)
            counter.start()
        }
    }

    override fun onTag(view: View) {
        tagBottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "a")

    }

    // FUNCTION OF CLICK LISTENER OF TAG BOTTOM SHEET DIALOG
    override fun clickSelectTag(view: View) {
        val binding = DataBindingUtil.findBinding<TagBottomSheetDialogRowBinding>(view)
        binding?.let {
            it.tag?.let {
               selectedTag = it

                val myView = getView()
                myView?.let{
                    val binding2 = DataBindingUtil.findBinding<FragmentPomodoroBinding>(it)
                    binding2?.let{
                        // change the buttons (stop and resume will be visible)
                        binding2.tagName.text = selectedTag.tagName
                        tagID = selectedTag.uuid
                    }
                }
            }
        }
        tagBottomSheetDialogFragment.dismiss()
    }
}