package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
class Planning(@ColumnInfo(name = "planningText")
               val planningText: String,
               @ColumnInfo(name = "tagId")
               val tagId: Int = 0,
               @ColumnInfo(name = "planningDate")
               val planningData: Date
)
{
    @PrimaryKey(true)
    var uuid : Int = 0



}