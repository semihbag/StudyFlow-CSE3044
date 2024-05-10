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
    val pomodoroId = MutableLiveData<Int>()

    // Pomodro mu Break mı onu ayırmak için bir variable
    // Defaultly 0 --> Pomodoro
    // 1 --> Break
    var check_p_b = 0

    // counter variables
    val totalTimeInMilsec = MutableLiveData<Long>()
    val remaingTimeInMilsec = MutableLiveData<Long>()

    // Dakika ve Saniyeleri ilk başta değer atıcaz
    fun setMinuteAndSecond(minutes: Long, seconds: Long, fragmentType: Int) {
        focusingMinutes.value = minutes
        focusingSeconds.value = seconds
        totalTimeInMilsec.value = (minutes * 60000) + (seconds * 1000)
        remaingTimeInMilsec.value = totalTimeInMilsec.value
        check_p_b = fragmentType
    }

    // Insert new Pomodoro Item to the Database
    fun insertPomodoro(pomodoro: Pomodoro){

        launch {
            val dao = StudyFlowDB(getApplication()).pomodoroDao()
            val id = dao.insertPomodoro(pomodoro)
            pomodoro.uuid = id.toInt()
            pomodoroId.value = pomodoro.uuid
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
        return  ((minToSec + secDif).times(1000) - totalTimeInMilsec.value!!).toLong()
    }
    // Verilen süreyi geri sayma işlemi burada yapılacak
    fun countDownTime(binding: FragmentPomodoroBinding, pomodoro_id: Int): CountDownTimer {
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
                    if (check_p_b == 0) {
                        val pomodoro = Pomodoro(calendarStart.value!!.timeInMillis, calendarEnd.value!!.timeInMillis,totalTimeInMilsec.value!!.toLong(),0,calculateInActiveTime(),-1)
                        insertPomodoro(pomodoro)
                    }
                    else {
                        updatePomodoro(totalTimeInMilsec.value!!,pomodoro_id)
                    }
                }
            }
        }
        return counter
    }

}