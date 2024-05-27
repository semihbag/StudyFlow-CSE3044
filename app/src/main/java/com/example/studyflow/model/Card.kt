package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Card(
    @ColumnInfo("cardTitle")
    var cardTitle : String?,
    @ColumnInfo("tagId")
    val tagId : Int = 0,
    @ColumnInfo("createDate")
    var createDate : Long,
    @ColumnInfo("textFront")
    var textFront : String?,
    @ColumnInfo("textBack")
    var textBack : String?,
    @ColumnInfo("imagePathFront")
    var imagePathFront : String?,
    @ColumnInfo("imagePathBack")
    var imagePathBack : String?,
    @ColumnInfo("isMarked")
    var isMarked : Boolean = true,
    @ColumnInfo("state")
    var state : Int = 1,
    @ColumnInfo("lastExerciseDate")
    var lastExerciseDate : Long,
    @ColumnInfo("lastAnswer")
    var lastAnswer : Boolean
) : Serializable {
    @PrimaryKey(true)
    var uuid : Int = 0
}