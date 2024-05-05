package com.example.studyflow.modelview

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyflow.model.ToDoPlan
import com.example.studyflow.service.DataBaseStudyFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ToDoFragmentModelView(application: Application): BaseViewModel(application) {

    val plans = MutableLiveData<List<ToDoPlan>>()

    fun initializePlansFromDB() {

        launch {
            val dao = DataBaseStudyFlow(getApplication()).getDaoObject()
            plans.value = dao.getAllPlans()

        }
    }
}