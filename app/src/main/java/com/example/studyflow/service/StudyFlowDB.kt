package com.example.studyflow.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studyflow.model.Tag
import com.example.studyflow.model.ToDo

// burada ne kadar entity varsa table olması için manuel eklememiz gerekli yazdıkça ekleriz şuan sadece tag ekliyorum


/*


    ÖNEMMLİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİ

    BURDA DAHA ÖNCE VERSİON 1 İDİ
    SONRA BAŞKA ENTİTY DAHA EKLEDİM DB YE
    YANİ ARKPALNDA YENİ TABLO OLUŞTURMALI
    O YÜZDEN VERSİON 2 YAPTIM
    HER SEFERİNDE VERSİON YÜKSELMELİ
    BU ÖNEMLİ

    DAO YU DA EKLEMEYİ UNUTMAYIN

*/
@Database(entities = [Tag::class, ToDo::class], version = 2)
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