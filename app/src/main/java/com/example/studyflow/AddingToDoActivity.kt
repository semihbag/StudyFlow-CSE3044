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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_to_do)

        plan= findViewById(R.id.toDoPlanText)
        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            if (plan.text.isEmpty()) {
                Toast.makeText(this,"Please enter a plan",Toast.LENGTH_LONG).show()
            }
            else {
                val db: MyDataBaseHelper = MyDataBaseHelper(this)
                db.addItem(plan.text.toString().trim())
                Toast.makeText(this,"The plan is added succesfully",Toast.LENGTH_LONG).show()
                val resultIntent = Intent(this,ToDoActivity::class.java)
                setResult(Activity.RESULT_OK,resultIntent)
                finish()
            }
        }
    }

}