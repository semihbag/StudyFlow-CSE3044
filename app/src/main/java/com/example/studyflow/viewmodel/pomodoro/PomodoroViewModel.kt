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
    fun countDownTime(view: View) {

        // Hepsini milisecond türünden tutuyorum
        var totalMin = focusingMinutes.value!!.times(60000)
        var totalSec = focusingSeconds.value!!.times(1000)
        var totalTime = totalMin + totalSec

        // Her starta basıldığında countDownTime çalışmalı
        // burada da database tablosundaki start columnu için Calendar objesi oluşturuluyor ilk kez
        if (calendarStart.value == null) {
            calendarStart.value = Calendar.getInstance()
        }

        object: CountDownTimer(totalTime,1000) {

            // Her bir arrivale ulaştığında güncelle
            override fun onTick(millisUntilFinished: Long) {
                view.findViewById<EditText>(R.id.Minutes).setText((totalTime / 60000).toString())
                view.findViewById<EditText>(R.id.Seconds).setText((totalTime % 60000).toString())
            }

            override fun onFinish() {
                // Toplam süre bittiğinde de obje initialize edilip
                // focus kısma geçilmeli
                calendarEnd.value = Calendar.getInstance()
                println("The operation is done")
            }

        }
    }

}