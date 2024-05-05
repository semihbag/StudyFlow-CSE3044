package com.example.studyflow.viewmodel.todoviewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Tag
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class ToDoViewModel (application: Application) : BaseViewModel(application) {
    val mutableSelectTagList = MutableLiveData<List<Tag>>()


    fun loadSelectTagFromDB() {
        launch {
            val dao = StudyFlowDB(getApplication()).tagDao()
            val currentSelectTags = dao.getAllTag()
            mutableSelectTagList.value = currentSelectTags
        }
    }
}