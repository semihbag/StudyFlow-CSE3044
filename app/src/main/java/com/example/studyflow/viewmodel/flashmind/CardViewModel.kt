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

            val card1 = Card("one",0,0,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","bbbb","a","a",true,1,0,false)
            val card2 = Card("one",0,0,"lorem","bbbb","a","a",true,1,0,false)
            val card3 = Card("one",0,0,"aaaaaaaaaaaaaaaaaaaaaa","bbbb","a","a",true,1,0,false)
            val card4 = Card("one",0,0,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","bbbb","a","a",true,1,0,false)

            val cs = arrayListOf<Card>(card1,card2,card3,card4)

            mutableCard.value = cs
        }
    }
}