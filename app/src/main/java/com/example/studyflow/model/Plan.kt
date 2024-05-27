package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plan (
    @ColumnInfo(name = "planText")
    val planText : String,
    @ColumnInfo("date")
    val date : Long
) {
    @PrimaryKey(true)
    var uuid : Int = 0
}