package com.example.studyflow.viewmodel.tag

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Tag
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class TagBottomSheetDialogViewModel(application: Application) : BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoTag = dB.tagDao()


    val mutableSelectableTagList = MutableLiveData<List<Tag>>()

    fun loadSelectableTagListFromDB() {

        launch {
            val currentSelectableTaglist = daoTag.getAllTag()
            mutableSelectableTagList.value = currentSelectableTaglist
        }

    }
}