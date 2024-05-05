package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyflow.model.ToDoPlan

@Dao
interface DaoToDoPlan {

    @Insert
    suspend fun insertAll(vararg plan: ToDoPlan): List<Long>

    @Query("SELECT * FROM ToDoPlan")
    suspend fun getAllPlans(): List<ToDoPlan>

    @Query("SELECT * FROM ToDoPlan WHERE id = :plan_id")
    suspend fun getSpesificPlan(plan_id : Int): ToDoPlan

    @Query("DELETE FROM ToDoPlan Where id = :plan_id")
    suspend fun deleteSpesificPlan(plan_id: Int)

    @Query("UPDATE ToDoPlan SET toDoPlan = :plan WHERE id = :plan_id")
    suspend fun updateSpesificPlan(plan: String, plan_id: Int)

}