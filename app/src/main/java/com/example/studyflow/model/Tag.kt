package com.example.studyflow.model

data class Tag (
    val tagName: String,
    val totalNumberOfPomodoro: Int = 0,
    val totalNumberOfCard: Int = 0,
    val totalNumberOfFocusedMinute: Int = 0,
    val totalNumberOfOutOfFocusedMinute: Int = 0,
    val totalNumberOfStop: Int = 0,
    val totalNumberOfCurrentCorrectAnswer: Int = 0
    ) {
}