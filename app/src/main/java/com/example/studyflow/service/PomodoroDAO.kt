package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.model.Tag

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

    // Update Tag Values

    @Query("UPDATE Tag SET totalNumberOfPomodoro = totalNumberOfPomodoro + 1 WHERE Tag.uuid = :tagId")
    suspend fun increasePomodorCount(tagId: Int)

    @Query("UPDATE Tag Set totalNumberOfFocusedMinute = totalNumberOfFocusedMinute + :minute WHERE Tag.uuid = :tagId")
    suspend fun increaseFocusedMinute(minute: Int, tagId: Int)

    @Query("UPDATE Tag Set totalNumberOfOutOfFocusedMinute = totalNumberOfOutOfFocusedMinute + :minute WHERE Tag.uuid = :tagId")
    suspend fun increaseOutOfFocusedMinute(minute: Int, tagId: Int)

    @Query("UPDATE Tag set totalNumberOfStop = totalNumberOfStop + 1 WHERE Tag.uuid = :tagId")
    suspend fun increaseStopCount(tagId: Int)

}