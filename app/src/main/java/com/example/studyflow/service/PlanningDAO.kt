package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyflow.model.Plan

@Dao
interface PlanningDAO {

    @Insert
    suspend fun insertPlanning(plan: Plan) : Long
    //döndürdüğü long id ama bunu modele atmadık sadece db bize bi key verdi burda

    @Query ("SELECT * FROM `plan`")
    suspend fun getAllPlanning(): List<Plan>

    @Query ("SELECT * FROM `plan` WHERE uuid =:planningId")
    suspend fun getPlanning(planningId: Int) : Plan

    @Query ("DELETE FROM `plan`")
    suspend fun deleteAllPlanning()

    @Query ("DELETE FROM `plan` WHERE uuid= :planningId")
    suspend fun deletePlanning(planningId: Int)

    @Query ("SELECT * FROM `plan` WHERE date= :date")
    suspend fun getPlanningWithGivenDate(date :Long) :List<Plan>

}