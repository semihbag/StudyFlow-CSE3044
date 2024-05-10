package com.example.studyflow.viewmodel.pomodoro

import android.app.Application
import android.os.CountDownTimer
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat.FocusDirection
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPomodoroBinding
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.view.FocusFragment
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
open class PomodoroViewModel(application: Application) : BaseViewModel(application) {

    // pomodoro variables
    val focusingMinutes = MutableLiveData<Long>()
    val focusingSeconds = MutableLiveData<Long>()
    val calendarStart = MutableLiveData<Calendar>()
    val calendarEnd = MutableLiveData<Calendar>()
    var pomodoroID : Int? = null

    // counter variables
    val totalTimeInMilsec = MutableLiveData<Long>()
    val remaingTimeInMilsec = MutableLiveData<Long>()
    val enteredTimeInMilsec = MutableLiveData<Long>()

    // Dakika ve Saniyeleri ilk başta değer atıcaz
    fun setMinuteAndSecond(minutes: Long, seconds: Long) {
        focusingMinutes.value = minutes
        focusingSeconds.value = seconds
        totalTimeInMilsec.value = (minutes * 60000) + (seconds * 1000)
        remaingTimeInMilsec.value = totalTimeInMilsec.value
        enteredTimeInMilsec.value = totalTimeInMilsec.value
    }

    // Insert new Pomodoro Item to the Database
    fun insertPomodoro(pomodoro: Pomodoro){
        launch {
            val dao = StudyFlowDB(getApplication()).pomodoroDao()
            val id = dao.insertPomodoro(pomodoro)
            pomodoro.uuid = id.toInt()
            pomodoroID = pomodoro.uuid
        }
    }

    fun updatePomodoro(breakTimeInMills: Long, pomodoro_id: Int) {
        launch {
            val dao = StudyFlowDB(getApplication()).pomodoroDao()
            dao.updateBreakTime(breakTimeInMills,pomodoro_id)
        }
    }

    // InActiveTıme = EndTime - StartTime milisaniye türünde
    fun calculateInActiveTime(): Long {
        val minToSec = (calendarEnd.value!!.get(Calendar.MINUTE) - calendarStart.value!!.get(Calendar.MINUTE)).times(60)
        val secDif = calendarEnd.value!!.get(Calendar.SECOND) - calendarStart.value!!.get(Calendar.SECOND)
        // farkı milisaniye olarak depoluyorum
        println(minToSec)
        println(secDif)
        println(minToSec + secDif)
        println(totalTimeInMilsec.value)
        return  ((minToSec + secDif).times(1000) - totalTimeInMilsec.value!!).toLong()
    }

    // Verilen süreyi geri sayma işlemi burada yapılacak
    fun countDownTime(binding: FragmentPomodoroBinding): CountDownTimer {
        // burada da database tablosundaki start columnu için Calendar objesi oluşturuluyor ilk kez
        if (calendarStart.value == null) {
            // Anlık zamanı milisaniye türünde alma
            val calendarObject = Calendar.getInstance()
            calendarObject.timeInMillis = System.currentTimeMillis()
            calendarStart.value = calendarObject

        }

        val counter =  object : CountDownTimer(remaingTimeInMilsec.value!!, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                remaingTimeInMilsec.value = remaingTimeInMilsec.value?.minus(1000L)
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
                calendarEnd.value = calendarObject
                if (calendarStart.value != null && calendarEnd.value != null) {
                    if (pomodoroID == null) {
                        var pomodoro: Pomodoro? = null
                        if (enteredTimeInMilsec.value == totalTimeInMilsec.value) {
                            pomodoro = Pomodoro(calendarStart.value!!.timeInMillis, calendarEnd.value!!.timeInMillis,enteredTimeInMilsec.value!!.toLong(),totalTimeInMilsec.value!!.toLong(),0,calculateInActiveTime(),-1,1)
                        }
                        else {
                            pomodoro = Pomodoro(calendarStart.value!!.timeInMillis, calendarEnd.value!!.timeInMillis,enteredTimeInMilsec.value!!.toLong(),totalTimeInMilsec.value!!.toLong(),0,calculateInActiveTime(),-1,0)
                        }
                        insertPomodoro(pomodoro)
                        binding.InfoText.setText("Break")
                    }
                    else {
                        updatePomodoro(totalTimeInMilsec.value!!, pomodoroID!!)
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

}