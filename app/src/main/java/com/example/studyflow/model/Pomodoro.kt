package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Pomodoro (
    @ColumnInfo(name = "startTime")
    val startTime: Long,
    @ColumnInfo(name = "endTime")
    val endTime: Long,
    @ColumnInfo(name = "EnteredTime")
    val enteredTime: Long,
    @ColumnInfo(name = "pomodoroTime")
    val pomodoroTime: Long,
    @ColumnInfo(name = "breakTime")
    val breakTime: Long,
    @ColumnInfo(name = "inactiveTime")
    val inactiveTime: Long,
    @ColumnInfo(name= "tagId")
    val tagId: Int,
    @ColumnInfo(name= "isFinished")
    val isFinished: Int

) {
    @PrimaryKey(true)
    var uuid : Int = 0
}