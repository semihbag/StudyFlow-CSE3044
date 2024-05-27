package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity
data class Plan (
    @ColumnInfo(name = "planText")
    val planText : String,
    @ColumnInfo("tagId")
    val tagId : Int,
    @ColumnInfo("date")
    val date : Long = Calendar.getInstance().timeInMillis
) {
    @PrimaryKey(true)
    var uuid : Int = 0
}