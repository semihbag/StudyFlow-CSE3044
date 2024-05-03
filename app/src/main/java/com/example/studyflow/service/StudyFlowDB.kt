package com.example.studyflow.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studyflow.model.Tag

// burada ne kadar entity varsa table olması için manuel eklememiz gerekli yazdıkça ekleriz şuan sadece tag ekliyorum
@Database(entities = arrayOf(Tag::class), version = 1)
abstract class StudyFlowDB : RoomDatabase() {
    abstract fun tagDao() : TagDAO


    // SINGLETON - OOP DERSINDE DE GORMUSTUK BUNU BI DESING PATTERN
    // sadede 1 tane database objesi oluşturmaya yarayacak bu
    // daha önceden oluşturmuşsak onu return edecek

    companion object {
        @Volatile private var instance : StudyFlowDB? = null

        private val lock = Any()
        operator fun invoke(contex: Context) = instance ?: synchronized(lock) {
            instance ?: createDB(contex).also {
                instance = it
            }
        }
        private fun createDB(contex : Context) = Room.databaseBuilder(contex.applicationContext,StudyFlowDB::class.java, "studyflowdatabase").build()

    }


}