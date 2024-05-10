package com.example.studyflow.interfaces.pomodoro

import android.view.View

open interface PomodoroFragmentClickListener {

    fun onStart(view: View)

    fun onStop(view: View)

    fun onPause(view: View)

    fun onResume(view: View)
}