package com.example.studyflow.modelview

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyflow.model.ToDoPlan
import java.lang.Exception

class ToDoFragmentModelView(): ViewModel() {

    val plans = MutableLiveData<List<ToDoPlan>>()
    val planArr = ArrayList<ToDoPlan>()

    fun initializePlansFromDB(context: Context) {

        planArr.clear()

        try {
            context.let {

                val database = it.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
                val cursor = database.rawQuery("SELECT * FROM toDoPlans",null)
                val planIndex = cursor.getColumnIndex("toDoPlan")
                val checkedIndex = cursor.getColumnIndex("is_checked")
                val indicesIndex = cursor.getColumnIndex("id")

                while (cursor.moveToNext()) {
                    planArr.add(ToDoPlan(cursor.getInt(indicesIndex),cursor.getString(planIndex),cursor.getInt(checkedIndex)))
                }
                cursor.close()
                this.plans.value= planArr
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }


}