package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ToDoPlan(
    @ColumnInfo(name = "toDoPlan")
    var plan: String,
    @ColumnInfo(name = "is_checked")
    val is_check: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}