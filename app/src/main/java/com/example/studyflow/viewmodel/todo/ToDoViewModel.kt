package com.example.studyflow.viewmodel.todo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Tag
import com.example.studyflow.model.ToDo
import com.example.studyflow.model.ToDoMainRecyclerItem
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class ToDoViewModel (application: Application) : BaseViewModel(application) {
    val mutableSelectTagList = MutableLiveData<List<Tag>>()
    val mutableToDoList = MutableLiveData<List<ToDo>>()
    val mutableToDoMainRecyclerItem = MutableLiveData<List<ToDoMainRecyclerItem>>()

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
        }
    }
    fun setToDoMainRecyclerItemList() {
        val newToDoMainRecyclerItemList = ArrayList<ToDoMainRecyclerItem>()


        launch {
            val daoToDo = StudyFlowDB(getApplication()).toDoDao()
            val daoTag = StudyFlowDB(getApplication()).tagDao()

            // burası general todolar ,.,n burdaki tag geçici db de yer almayacak sadece general başlığı için
            val generalTag = Tag("General")
            val generalTodos = daoToDo.getAllToDoWithGivenTagId(0)
            if (generalTodos.size != 0) {
                val generalToDoMainRecyclerItem = ToDoMainRecyclerItem(generalTag, ArrayList(generalTodos))
                newToDoMainRecyclerItemList.add(generalToDoMainRecyclerItem)
            }

            val allTags = daoTag.getAllTag()
            for (tag in allTags){
                val toDoListInSpecificTag = daoToDo.getAllToDoWithGivenTagId(tag.uuid)
                if (toDoListInSpecificTag.size != 0) {
                    val mainItem = ToDoMainRecyclerItem(tag, ArrayList(toDoListInSpecificTag))
                    newToDoMainRecyclerItemList.add(mainItem)
                }
            }

            mutableToDoMainRecyclerItem.value = newToDoMainRecyclerItemList
        }
    }
}