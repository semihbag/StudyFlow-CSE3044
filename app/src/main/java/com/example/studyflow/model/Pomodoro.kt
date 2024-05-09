package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pomodoro (
    @ColumnInfo(name = "startTime")
    val startTime: Long,
    @ColumnInfo(name = "endTime")
    val endTime: Long,
    @ColumnInfo(name = "pomodoroTime")
    val pomodoroTime: Long,
    @ColumnInfo(name = "breakTime")
    val breakTime: Int,
    @ColumnInfo(name = "inactiveTime")
    val inactiveTime: Long,
    @ColumnInfo(name= "tagId")
    val tagId: Int
) {
    @PrimaryKey(true)
    var uuid : Int = 0
}