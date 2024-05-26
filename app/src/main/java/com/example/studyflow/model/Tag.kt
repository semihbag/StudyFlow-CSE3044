package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Tag (
    @ColumnInfo(name = "tagName")
    val tagName: String,
    @ColumnInfo(name = "totalNumberOfPomodoro") //
    val totalNumberOfPomodoro: Int = 0,
    @ColumnInfo(name = "totalNumberOfCard")
    var totalNumberOfCard: Int = 0,
    @ColumnInfo(name = "totalNumberOfTodos")
    var totalNumberOfTodos: Int = 0,
    @ColumnInfo(name = "totalNumberOfFocusedMinute") //
    val totalNumberOfFocusedMinute: Int = 0,
    @ColumnInfo(name = "totalNumberOfOutOfFocusedMinute") //
    val totalNumberOfOutOfFocusedMinute: Int = 0,
    @ColumnInfo(name = "totalNumberOfStop") //
    val totalNumberOfStop: Int = 0,
    @ColumnInfo(name = "totalNumberOfCurrentCorrectAnswer")
    val totalNumberOfCurrentCorrectAnswer: Int = 0,
    @ColumnInfo("totalNumberOdCurrentFalseAnswer")
    var totalNumberOdCurrentFalseAnswer : Int  =0,
    @ColumnInfo(name = "cardRatio")
    var cardRatio: Int = 0,
    @ColumnInfo("totalNumberOfTodoDone")
    var totalNumberOfTodoDone: Int = 0,
    @ColumnInfo("todoRatio")
    var todoRatio : Int = 0
) : Serializable {
    @PrimaryKey(true)
    var uuid : Int = 0
}