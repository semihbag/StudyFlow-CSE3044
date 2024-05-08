package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDo (
    @ColumnInfo(name = "toDoText")
    val toDoText : String,
    @ColumnInfo(name = "tagId")
    val tagId : Int = 0,
    @ColumnInfo(name = "done")
    var done : Boolean
) {
    @PrimaryKey(true)
    var uuid : Int = 0
}