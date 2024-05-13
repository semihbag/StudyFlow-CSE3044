package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    @ColumnInfo("cardTitle")
    val cardTitle : String,
    @ColumnInfo("tagId")
    val tagId : Int = 0,
    @ColumnInfo("createDate")
    val createDate : Long,
    @ColumnInfo("textFront")
    val textFront : String?,
    @ColumnInfo("textBack")
    val textBack : String?,
    @ColumnInfo("imagePathFront")
    val imagePathFront : String?,
    @ColumnInfo("imagePathBack")
    val imagePathBack : String?,
    @ColumnInfo("isMarked")
    val isMarked : Boolean = true,
    @ColumnInfo("state")
    val state : Int = 1,
    @ColumnInfo("lastExerciseDate")
    val lastExerciseDate : Long,
    @ColumnInfo("lastAnswer")
    val lastAnswer : Boolean
) {
    @PrimaryKey(true)
    var uuid : Int = 0
}