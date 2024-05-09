package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import com.example.studyflow.model.Pomodoro

@Dao
interface PomodoroDAO {

    @Insert
    suspend fun insertPomodoro(pomodoro: Pomodoro) : Long


}