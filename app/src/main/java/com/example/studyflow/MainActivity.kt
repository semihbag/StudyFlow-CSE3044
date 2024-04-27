package com.example.studyflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goFocusPage(view: View) {
        val intent = Intent(applicationContext, FocusActivity::class.java)
        startActivity(intent)
    }

    fun goFlashMindPage(view: View) {
        val intent = Intent(applicationContext, FlashMindActivity::class.java)
        startActivity(intent)
    }

    fun goPlanningPage(view: View) {
        val intent = Intent(applicationContext, PlanningActivity::class.java)
        startActivity(intent)
    }

    fun goToDoPage(view: View) {
        val intent = Intent(applicationContext, ToDoActivity::class.java)
        startActivity(intent)
    }

    fun goAnalysisPage(view: View) {
        val intent = Intent(applicationContext, AnalysisActivity::class.java)
        startActivity(intent)
    }

}