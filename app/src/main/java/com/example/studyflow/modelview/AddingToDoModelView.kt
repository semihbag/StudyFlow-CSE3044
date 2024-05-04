package com.example.studyflow.modelview

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyflow.R
import java.lang.Exception

class AddingToDoModelView: ViewModel() {

    var add_Plan = MutableLiveData<String>()

    fun setPlan(view: View) {
        add_Plan.value = view.findViewById<EditText>(R.id.toDoPlanText).text.toString()
    }

    fun insertPlan(context: Context) {

        try {
            context.let {
                val database = it.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS toDoPlans (id INTEGER PRIMARY KEY, toDoPlan VARCHAR, is_checked BIT(1))")

                val sqlString = "INSERT INTO toDoPlans (toDoPlan,is_checked) VALUES (?,0)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,add_Plan.value)
                statement.execute()
                Toast.makeText(it,"The Plan is added successfully", Toast.LENGTH_LONG).show()
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

}