package com.example.studyflow.viewmodel.pomodoro

import android.app.Application
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.R
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
open class BreakViewModel(application: Application) : BaseViewModel(application) {

    // pomodoro variables
    val focusingMinutes = MutableLiveData<Long>()
    val focusingSeconds = MutableLiveData<Long>()
    val calendarStart = MutableLiveData<Calendar>()
    val calendarEnd = MutableLiveData<Calendar>()

    // counter variables
    val totalTimeInMilsec = MutableLiveData<Long>()
    val remaingTimeInMilsec = MutableLiveData<Long>()

    // Dakika ve Saniyeleri ilk başta değer atıcaz
    fun setMinuteAndSecond(minutes: Long, seconds: Long) {
        focusingMinutes.value = minutes
        focusingSeconds.value = seconds
        totalTimeInMilsec.value = (minutes * 60000) + (seconds * 1000)
        remaingTimeInMilsec.value = totalTimeInMilsec.value
    }

    // Insert new Pomodoro Item to the Database
    fun insertPomodoro(pomodoro: Pomodoro) {

        launch {
            val dao = StudyFlowDB(getApplication()).pomodoroDao()
            val id = dao.insertPomodoro(pomodoro)
            pomodoro.uuid = id.toInt()
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
    fun countDownTime(minutesEditText: EditText, secondsEditText: EditText): CountDownTimer {
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
                    minutesEditText.setText("0" + (millisUntilFinished / 60000).toString())
                }
                else {
                    minutesEditText.setText((millisUntilFinished / 60000).toString())
                }
                if ( ((millisUntilFinished % 60000) / 1000) < 10) {
                    secondsEditText.setText("0" + ((millisUntilFinished % 60000) / 1000).toString())
                }
                else {
                    secondsEditText.setText(((millisUntilFinished % 60000) / 1000).toString())
                }
            }

            override fun onFinish() {
                // Toplam süre bittiğinde de obje initialize edilip
                // focus kısma geçilmeli
                val calendarObject = Calendar.getInstance()
                calendarObject.timeInMillis = System.currentTimeMillis()
                calendarEnd.value = calendarObject
                if (calendarStart.value != null && calendarEnd.value != null) {
                    insertPomodoro(Pomodoro(calendarStart.value!!.timeInMillis,
                        calendarEnd.value!!.timeInMillis,totalTimeInMilsec.value!!.toLong(),0,calculateInActiveTime(),-1))

                }
            }

        }
        return counter

    }

}