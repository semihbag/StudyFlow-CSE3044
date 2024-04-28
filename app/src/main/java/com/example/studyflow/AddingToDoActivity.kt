package com.example.studyflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddingToDoActivity : AppCompatActivity() {

    private lateinit var addButton: Button
    private lateinit var plan: EditText
    private lateinit var messages: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_to_do)

        messages = intent.getStringArrayListExtra("all_plans") as ArrayList<String>
        plan= findViewById(R.id.toDoPlanText)
        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            if (plan.text.isEmpty()) {
                Toast.makeText(this,"Please enter a plan",Toast.LENGTH_LONG).show()
            }
            else {
                messages.add(plan.text.toString())
                val resultIntent = Intent()
                resultIntent.putExtra("updated_messages",messages)
                setResult(Activity.RESULT_OK, resultIntent)
                Toast.makeText(this,"The plan is added successfully",Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

}