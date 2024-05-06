package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyflow.model.ToDo

@Dao
interface ToDoDAO {
    @Insert
    suspend fun insertToDo(toDo : ToDo) : Long

    @Insert
    suspend fun insertAllToDo(vararg toDo : ToDo) : List<Long>

    @Query("SELECT * FROM todo")
    suspend fun getAllToDo() : List<ToDo>

    @Query("SELECT * FROM todo WHERE uuid = :toDoId")
    suspend fun getToDo(toDoId : Int) : ToDo

    @Query("SELECT * FROM todo WHERE tagId = :givenTagId")
    suspend fun getAllToDoWithGivenTagId(givenTagId : Int) : List<ToDo>


    @Query("SELECT * FROM todo WHERE tagId = :givenTagId and done = :givenDone")
    suspend fun  getAllToDoWithGivenTagIdAndGivenDone(givenTagId : Int, givenDone : Boolean) : List<ToDo>


    @Query("DELETE FROM todo")
    suspend fun deleteAllToDo()

    @Query("DELETE FROM todo WHERE uuid = :toDoId")
    suspend fun deleteToDo(toDoId :Int)
}