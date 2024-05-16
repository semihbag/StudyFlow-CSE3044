package com.example.studyflow.viewmodel.flashmind

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Card
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoCard = dB.cardDao()

    val mutableCard = MutableLiveData<List<Card>>()

    fun loadCardsFromDB() {

        launch {
            val currentCards = daoCard.getAllCard()
            mutableCard.value = currentCards
        }
    }
}