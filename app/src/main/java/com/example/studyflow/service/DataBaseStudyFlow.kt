package com.example.studyflow.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studyflow.model.ToDoPlan


@Database(entities = arrayOf(ToDoPlan::class), version = 1)
abstract class DataBaseStudyFlow: RoomDatabase() {

    abstract fun getDaoObject(): DaoToDoPlan

    companion object {

        @Volatile private var instance: DataBaseStudyFlow? = null

        private var lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDataBase(context).also {
                instance = it
            }
        }
        private fun createDataBase(context: Context) = Room.databaseBuilder(context.applicationContext,DataBaseStudyFlow::class.java,"StudyFlow").build()
    }

}