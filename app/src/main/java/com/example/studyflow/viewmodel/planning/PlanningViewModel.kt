package com.example.studyflow.viewmodel.planning

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Plan
import com.example.studyflow.model.Tag
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class PlanningViewModel (application: Application): BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoPlanning= dB.planningDao()
    private val daoTag = dB.tagDao()



    val mutableSelectPlanningDay= MutableLiveData<List<Plan>>()
    val mutableSelectTagList = MutableLiveData<List<Tag>>()



    //Bu metot sadece seçilen tarihlerin yüklenmesini sağlayacak
    //Planning fragmentta onviewcreatedda çağırılacak
    //çağırıldığı yer zaten fragment ve orda seçilen tarihi alabiliyoruz
    //bu metoda yollayıp daoya yazdığmız querylere bir de tarihe özel data çekme querysi ekleriz
    fun loadPlanningFromDB(selectedDate :Long ) {

        launch {
            val currentSelectPlanningDay =daoPlanning.getPlanningWithGivenDate(selectedDate)
            mutableSelectPlanningDay.value =currentSelectPlanningDay
        }
    }

    //save butonuna tıklandığında çağırılacak metot
    fun storePlanningToDB (planning : Plan){
        println(planning)
        launch {
            val id =daoPlanning.insertPlanning(planning)
            planning.uuid =id.toInt()
            val currentPlannings =mutableSelectPlanningDay.value?.toMutableList() ?: mutableListOf()
            currentPlannings.add(planning)
            mutableSelectPlanningDay.value =currentPlannings
            //liste boşsa liste oluştur
            //gönderilen planı ekle
            //sonra listeye ekle bitti
        }

    }




}


