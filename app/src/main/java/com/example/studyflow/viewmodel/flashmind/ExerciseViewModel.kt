package com.example.studyflow.viewmodel.flashmind

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.model.Card
import com.example.studyflow.model.Tag
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoTag = dB.tagDao()
    private val daoCard = dB.cardDao()

    val mutableMarkedCardList = MutableLiveData<List<Card>>()

    fun loadMarkedCardsFromDB(tag : Tag) {

        launch {

            val currentMarkedCards = daoCard.getAllCardWithGivenTagIdAndGivenMarked(tag.uuid,true)
            mutableMarkedCardList.value = currentMarkedCards
            println(currentMarkedCards.size)

        }
    }

    fun updateCard(tag : Tag, card : Card) {

        launch {

            // update linked tag
            // sonra yazcam

            daoCard.updateCard(card)
            loadMarkedCardsFromDB(tag)
        }

    }
}