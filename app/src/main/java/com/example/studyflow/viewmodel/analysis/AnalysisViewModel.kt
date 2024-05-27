package com.example.studyflow.viewmodel.analysis

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

open class AnalysisViewModel(application: Application) : BaseViewModel(application) {

    var pomodoroItems = MutableLiveData<List<Pomodoro>>()

    fun findAllPomodorosOfSpesificTag(tag_id: Int) {

        launch {
            val dao = StudyFlowDB(getApplication()).pomodoroDao()
            pomodoroItems.value = dao.getSpesificTagTypePomodoros(tag_id)
        }
    }
}