package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Card(
    @ColumnInfo("cardTitle")
    var cardTitle : String,
    @ColumnInfo("tagId")
    val tagId : Int = 0,
    @ColumnInfo("createDate")
    val createDate : Long,
    @ColumnInfo("textFront")
    var textFront : String?,
    @ColumnInfo("textBack")
    var textBack : String?,
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
) : Serializable {
    @PrimaryKey(true)
    var uuid : Int = 0
}