package com.example.studyflow.modelview

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyflow.R
import com.example.studyflow.model.ToDoPlan
import com.example.studyflow.service.DataBaseStudyFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class AddingToDoModelView(application: Application): BaseViewModel(application) {

    var add_Plan = MutableLiveData<String>()

    fun setPlan(view: View) {
        add_Plan.value = view.findViewById<EditText>(R.id.toDoPlanText).text.toString()
        println(add_Plan.value.toString())
    }

    fun insertPlan() {

        launch {
            val dao = DataBaseStudyFlow(getApplication()).getDaoObject()
            val added_Object = ToDoPlan(add_Plan.value.toString(),0)
            val listIndex = dao.insertAll(added_Object)
            added_Object.id = listIndex[0].toInt()

        }
    }

}