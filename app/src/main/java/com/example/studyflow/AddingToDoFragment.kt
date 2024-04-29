package com.example.studyflow

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class AddingToDoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adding_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addButton = view.findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val toDoPlanText = view.findViewById<EditText>(R.id.toDoPlanText).text.toString()
            try {
                context?.let {
                    val database = it.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
                    database.execSQL("CREATE TABLE IF NOT EXISTS toDoPlans (id INTEGER PRIMARY KEY, toDoPlan VARCHAR, is_checked BIT(1))")

                    val sqlString = "INSERT INTO toDoPlans (toDoPlan,is_checked) VALUES (?,0)"
                    val statement = database.compileStatement(sqlString)
                    statement.bindString(1,toDoPlanText)
                    statement.execute()
                    Toast.makeText(it,"The Plan is added successfully",Toast.LENGTH_LONG).show()
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}