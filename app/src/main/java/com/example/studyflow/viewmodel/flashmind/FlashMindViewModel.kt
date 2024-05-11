package com.example.studyflow.viewmodel.flashmind

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Tag
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class FlashMindViewModel(application: Application) : BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoTag = dB.tagDao()
    private val daoToDo = dB.toDoDao()

    val mutableFlashMindTags = MutableLiveData<List<Tag>>()

    fun loadFlashMindTagsFromDB() {

        launch {
            val currentTags = daoTag.getAllTag()
            mutableFlashMindTags.value = currentTags
        }
        
    }
}