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
    private val dB = StudyFlowDB(getApplication())
    private val daoTag = dB.tagDao()
    private val daoToDo = dB.toDoDao()
    //yukarda oluşturmuş tek farkı bu mal zenep ğpodwefplweğp


    val mutableSelectTagList = MutableLiveData<List<Tag>>()
    val mutableToDoList = MutableLiveData<List<ToDo>>()
    val mutableToDoMainRecyclerItem = MutableLiveData<List<ToDoMainRecyclerItem>>()

    fun loadSelectTagFromDB() {
        launch {
            val currentSelectTags = daoTag.getAllTag()
            mutableSelectTagList.value = currentSelectTags
            //load yapacağı şeyler sadece seçilen todolar
        }
    }

    fun storeToDoToDB(toDo : ToDo) {

        launch {
            val id = daoToDo.insertToDo(toDo)
            toDo.uuid = id.toInt()
            val currentToDos = mutableToDoList.value?.toMutableList() ?: mutableListOf()
            currentToDos.add(toDo)
            mutableToDoList.value = currentToDos
        }
    }
    fun setToDoMainRecyclerItemList() {
        val newToDoMainRecyclerItemList = ArrayList<ToDoMainRecyclerItem>()

        launch {

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

    fun updateToDo(todo : ToDo) {
        println(todo.done)
        launch {
            daoToDo.updateToDo(todo)
        }
    }

}