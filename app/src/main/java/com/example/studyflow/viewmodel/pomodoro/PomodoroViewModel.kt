package com.example.studyflow.viewmodel.pomodoro

import android.app.Application
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.R
import com.example.studyflow.viewmodel.BaseViewModel
import java.util.*
open class PomodoroViewModel(application: Application) : BaseViewModel(application) {

    val focusingMinutes = MutableLiveData<Long>()
    val focusingSeconds = MutableLiveData<Long>()
    val calendarStart = MutableLiveData<Calendar>()
    val calendarEnd = MutableLiveData<Calendar>()

    // Dakika ve Saniyeleri ilk başta değer atıcaz
    fun setMinuteAndSecond(minutes: Long, seconds: Long) {
        focusingMinutes.value = minutes
        focusingSeconds.value = seconds
    }

    // Verilen süreyi geri sayma işlemi burada yapılacak
    fun countDownTime(minutesEditText: EditText, secondsEditText: EditText) {

        // Hepsini milisecond türünden tutuyorum
        var totalMin = focusingMinutes.value!!.times(60000)
        var totalSec = focusingSeconds.value!!.times(1000)
        var totalTime = totalMin + totalSec
        // Her starta basıldığında countDownTime çalışmalı
        // burada da database tablosundaki start columnu için Calendar objesi oluşturuluyor ilk kez
        if (calendarStart.value == null) {
            // Anlık zamanı milisaniye türünde alma
            val currentTimeInMill = System.currentTimeMillis()
            val calendarObject = Calendar.getInstance()
            calendarObject.timeInMillis = currentTimeInMill
            calendarStart.value = calendarObject
            
        }

        object : CountDownTimer(totalTime,1000) {
            override fun onTick(millisUntilFinished: Long) {
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
                val currentTimeInMill = System.currentTimeMillis()
                val calendarObject = Calendar.getInstance()
                calendarObject.timeInMillis = currentTimeInMill
                calendarEnd.value = calendarObject
            }

        }.start()

    }

}