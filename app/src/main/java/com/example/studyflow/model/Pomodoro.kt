package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pomodoro (
    @ColumnInfo(name = "startTime")
    val startTime: Int,
    @ColumnInfo(name = "endTime")
    val endTime: Int,
    @ColumnInfo(name = "pomodoroTime")
    val pomodoroTime: Int,
    @ColumnInfo(name = "breakTime")
    val breakTime: Int,
    @ColumnInfo(name = "inactiveTime")
    val inactiveTime: Int,
    @ColumnInfo(name= "tagId")
    val tagId: Int
) {
    @PrimaryKey(true)
    var uuid : Int = 0
}