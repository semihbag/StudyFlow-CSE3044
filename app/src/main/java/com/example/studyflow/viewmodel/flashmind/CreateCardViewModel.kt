package com.example.studyflow.viewmodel.flashmind

import android.app.Application
import android.widget.Toast
import com.example.studyflow.model.Card
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class CreateCardViewModel(application: Application) : BaseViewModel(application) {
    private val dB = StudyFlowDB(getApplication())
    private val daoTag = dB.tagDao()
    private val daoCard = dB.cardDao()

    fun storeCardToDB(card : Card) {

        launch {

            // update linked tag
            val tagUuid = card.tagId
            val tag = daoTag.getTag(tagUuid)
            tag.totalNumberOfCard = tag.totalNumberOfCard + 1
            tag.cardRatio = ((tag.totalNumberOfCurrentCorrectAnswer.toDouble() / tag.totalNumberOfCard.toDouble()) * 100).toInt()
            daoTag.updateTag(tag)

            // store card
            val id = daoCard.insertCard(card)
            card.uuid = id.toInt()
        }
    }
}