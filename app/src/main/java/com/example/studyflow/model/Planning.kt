package com.example.studyflow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.studyflow.interfaces.planning.PlanningFragmentClickListener
import java.util.Date

@Entity
class Planning(@ColumnInfo(name = "planningText")
               val planningText: PlanningFragmentClickListener,
               @ColumnInfo(name = "tagId")
               val tagId: Int = 0,
               @ColumnInfo(name = "planningDate")
               val planningData: Date
)
{
    @PrimaryKey(true)
    var uuid : Int = 0



}