package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag (
    @ColumnInfo(name = "tagName")
    val tagName: String,
    @ColumnInfo(name = "totalNumberOfPomodoro")
    val totalNumberOfPomodoro: Int = 0,
    @ColumnInfo(name = "totalNumberOfCard")
    val totalNumberOfCard: Int = 0,
    @ColumnInfo(name = "totalNumberOfFocusedMinute")
    val totalNumberOfFocusedMinute: Int = 0,
    @ColumnInfo(name = "totalNumberOfOutOfFocusedMinute")
    val totalNumberOfOutOfFocusedMinute: Int = 0,
    @ColumnInfo(name = "totalNumberOfStop")
    val totalNumberOfStop: Int = 0,
    @ColumnInfo(name = "totalNumberOfCurrentCorrectAnswer")
    val totalNumberOfCurrentCorrectAnswer: Int = 0,
    @ColumnInfo(name = "cardRatio")
    val cardRatio: Int = 0
) {
    @PrimaryKey(true)
    var uuid : Int = 0
}