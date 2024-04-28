package com.example.studyflow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoActivity : AppCompatActivity() {

    private var messages: ArrayList<String> = ArrayList<String>()
    private lateinit var customAdapterToDo: CustomAdapterToDo
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.floatingActionButton)
        addButton.setOnClickListener{
            val intent = Intent(this,AddingToDoActivity::class.java)
            intent.putExtra("all_plans",messages)
            startActivityForResult(intent,1)
        }

        customAdapterToDo = CustomAdapterToDo(this,this,messages)
        recyclerView.adapter = customAdapterToDo
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    messages = data.getStringArrayListExtra("updated_messages") as ArrayList<String>
                    customAdapterToDo = CustomAdapterToDo(this,this,messages)
                    recyclerView.adapter = customAdapterToDo
                    recyclerView.layoutManager = LinearLayoutManager(this)
                }
            }
        }
    }
}