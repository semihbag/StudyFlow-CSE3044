package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studyflow.model.ToDo

@Dao
interface ToDoDAO {

    // insert functions
    @Insert
    suspend fun insertToDo(toDo : ToDo) : Long

    @Insert
    suspend fun insertAllToDo(vararg toDo : ToDo) : List<Long>


    // get functions
    @Query("SELECT * FROM todo")
    suspend fun getAllToDo() : List<ToDo>

    @Query("SELECT * FROM todo WHERE uuid = :toDoId")
    suspend fun getToDo(toDoId : Int) : ToDo

    @Query("SELECT * FROM todo WHERE tagId = :givenTagId")
    suspend fun getAllToDoWithGivenTagId(givenTagId : Int) : List<ToDo>

    @Query("SELECT * FROM todo WHERE tagId = :givenTagId and done = :givenDone")
    suspend fun  getAllToDoWithGivenTagIdAndGivenDone(givenTagId : Int, givenDone : Boolean) : List<ToDo>


    // delete functions
    @Query("DELETE FROM todo")
    suspend fun deleteAllToDo()

    @Query("DELETE FROM todo WHERE uuid = :toDoId")
    suspend fun deleteToDo(toDoId :Int)


    // update functions
    @Update
    suspend fun updateToDo(toDo : ToDo)

}