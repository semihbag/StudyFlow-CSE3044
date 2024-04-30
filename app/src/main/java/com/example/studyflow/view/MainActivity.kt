package com.example.studyflow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.studyflow.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }






    // CHANGE PAGE FUNCTIONS
    fun goFocusPage(view: View) {
        val focusPageButton = findViewById<Button>(R.id.focusButton)
        focusPageButton.setOnClickListener {
            val action =
        }
    }
    fun goFlashMindPage(view: View) {

    }
    fun goPlanningPage(view: View) {

    }
    fun goToDoPage(view: View) {

    }
    fun goAnalysisPage(view: View) {

    }

    fun goTagPage(view: View) {

    }
}