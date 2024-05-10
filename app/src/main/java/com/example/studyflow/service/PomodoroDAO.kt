package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyflow.model.Pomodoro

@Dao
interface PomodoroDAO {

    @Insert
    suspend fun insertPomodoro(pomodoro: Pomodoro) : Long

    @Query("UPDATE Pomodoro SET breakTime = :breakTimeInMills WHERE uuid = :pomodoro_id")
    suspend fun updateBreakTime(breakTimeInMills: Long, pomodoro_id: Int)

    @Query("SELECT * FROM Pomodoro WHERE uuid = :pomodoro_id")
    suspend fun getSpesificPomodoro(pomodoro_id: Int): Pomodoro

    @Query("SELECT * FROM Pomodoro WHERE tagId = :tag_id")
    suspend fun getSpesificTagTypePomodoros(tag_id: Int): List<Pomodoro>


}