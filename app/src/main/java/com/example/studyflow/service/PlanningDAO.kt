package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyflow.model.Planning

@Dao
interface PlanningDAO {

    @Insert
    suspend fun insertPlanning(vararg planning: Planning) : Long
    //döndürdüğü long id ama bunu modele atmadık sadece db bize bi key verdi burda

    @Query ("SELECT * FROM planning")
    suspend fun getAllPlanning(): List<Planning>

    @Query ("SELECT * FROM planning WHERE uuid =:planningId")
    suspend fun getPlanning(planningId: Int) : Planning

    @Query ("DELETE FROM planning")
    suspend fun deleteAllPlanning()

    @Query ("DELETE FROM planning WHERE uuid= :planningId")
    suspend fun deletePlanning(planningId: Int)

}