package com.example.studyflow.viewmodel.planning

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Planning
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class PlanningViewModel (application: Application): BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoPlanning= dB.planningDao()
    private val daoTag = dB.tagDao()



    val mutablePlannings= MutableLiveData<List<Planning>>()


    /*
     fun loadTagsFromDB() {

        launch {
            val dao = StudyFlowDB(getApplication()).tagDao()
            val currentTags = dao.getAllTag()
            mutableTags.value = currentTags
        }
    }

     */
    fun loadPlanningsFromDB(){

        //seçilen tagin planlarını göstermicen dateine göre göstercen
        launch {

        }
    }

    fun storePlanningToDB (planning : Planning){
        launch {

        }

    }



}

