package com.example.studyflow.viewmodel.flashmind

import android.app.Application
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel

class CreateCardViewModel(application: Application) : BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoTag = dB.tagDao()

}