package com.example.studyflow

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteAndUpdatePlanActivity : AppCompatActivity() {

    private lateinit var updateButton : Button
    private lateinit var deleteButton : Button
    private lateinit var planText : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_and_update_plan)

        planText = findViewById(R.id.toDoPlanUD)
        getInfoFromToDoPage()
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)



    }

    private fun getInfoFromToDoPage() {

        if ( intent.hasExtra("plan")) {
                planText.setText(intent.getStringExtra("plan").toString())
            }
        else {
            Toast.makeText(this,"There is no information about that plan", Toast.LENGTH_LONG).show()
        }
    }

}