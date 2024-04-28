package com.example.studyflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ToDoActivity : AppCompatActivity() {

    private var messages: ArrayList<String> = ArrayList<String>()
    private lateinit var customAdapterToDo: CustomAdapterToDo
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        recyclerView = findViewById(R.id.recyclerView)

        messages.add("Hello my World")
        messages.add("Another String is created")
        customAdapterToDo = CustomAdapterToDo(this,this,messages)
        recyclerView.adapter = customAdapterToDo
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}