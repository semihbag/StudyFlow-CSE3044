package com.example.studyflow.viewmodel.todoviewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Tag
import com.example.studyflow.model.ToDo
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class ToDoViewModel (application: Application) : BaseViewModel(application) {
    val mutableSelectTagList = MutableLiveData<List<Tag>>()
    val mutableToDoList = MutableLiveData<List<ToDo>>()

    fun loadSelectTagFromDB() {
        launch {
            val dao = StudyFlowDB(getApplication()).tagDao()
            val currentSelectTags = dao.getAllTag()
            mutableSelectTagList.value = currentSelectTags
        }
    }

    fun storeToDoToDB(toDo : ToDo) {

        launch {
            val dao = StudyFlowDB(getApplication()).toDoDao()
            val id = dao.insertToDo(toDo)
            toDo.uuid = id.toInt()
            var currentToDos = mutableToDoList.value?.toMutableList() ?: mutableListOf()
            currentToDos.add(toDo)
            mutableToDoList.value = currentToDos
            println(id)
        }
    }
}