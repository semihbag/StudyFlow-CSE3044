package com.example.studyflow.modelview

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyflow.R
import com.example.studyflow.model.ToDoPlan
import com.example.studyflow.service.DataBaseStudyFlow
import kotlinx.coroutines.launch

class DeleteAndUpdateModelView(application: Application): BaseViewModel(application) {

    var toDoPlan = MutableLiveData<ToDoPlan>()

    fun initializePlan(id: Int) {

        if (id != -1) {
            launch {
                val dao = DataBaseStudyFlow(getApplication()).getDaoObject()
                toDoPlan.value = dao.getSpesificPlan(id)
            }
        }
    }

    fun updatePlan(plan: String, id: Int) {
        launch {
            val dao = DataBaseStudyFlow(getApplication()).getDaoObject()
            dao.updateSpesificPlan(plan,id)
        }
    }

    fun deletePlan(id: Int) {
        launch {
            val dao = DataBaseStudyFlow(getApplication()).getDaoObject()
            dao.deleteSpesificPlan(id)
        }
    }
}