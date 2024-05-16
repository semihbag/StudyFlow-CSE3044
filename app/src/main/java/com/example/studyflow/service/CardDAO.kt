package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studyflow.model.Card

@Dao
interface CardDAO {

    // insert functions
    @Insert
    suspend fun insertCard(card: Card): Long

    @Insert
    suspend fun insertAllCard(vararg card: Card): List<Long>


    // get functions
    @Query("SELECT * FROM card")
    suspend fun getAllCard(): List<Card>

    @Query("SELECT * FROM card WHERE uuid = :carId")
    suspend fun getCard(carId: Int): Card

    @Query("SELECT * FROM card WHERE tagId = :givenTagId")
    suspend fun getAllCardWithGivenTagId(givenTagId: Int): List<Card>

    @Query("SELECT * FROM card WHERE tagId = :givenTagId and isMarked = :givenMarked")
    suspend fun getAllCardWithGivenTagIdAndGivenMarked(givenTagId: Int, givenMarked : Boolean) : List<Card>


    // delete functions
    @Query("DELETE FROM card")
    suspend fun deleteAllCard()

    @Query("DELETE FROM card WHERE uuid = :carId")
    suspend fun deleteCard(carId: Int)


    // update functions
    @Update
    suspend fun updateCard(card: Card)


}
