package com.example.studyflow.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studyflow.model.Tag

@Dao
interface TagDAO {
    // Data Access Object
    // bu bir interface kısaca tag için ne zaman db işlemleri lazım olacaksa buradan yardım alacağız

    /*
        insert = insert into diye sql string yazıyorduk ya ona gerek yok direkt böyle yazcaz
        suspend = coroutine scope için lazım database aynı anda tek bir şey etkileşime geçmeli yoksa karışmasın
        vararg = argümnalar işte
        List<Long> döndürsün dedik çünkü bunlar db deki pk idleri olacak. bu idler ile ilgili taglar üzerinde işlem yapıcaz
        her tagin özel idsi var artık

     */


    // bu fonksiyonlar room kütüpün fonksiyonları biz bunları özellştiriyoruz
    @Insert
    suspend fun insertTag(tag : Tag) : Long

    @Insert
    suspend fun insertAllTag(vararg tag: Tag) : List<Long>

    @Query("SELECT * FROM tag")
    suspend fun getAllTag() : List<Tag>

    @Query("SELECT * FROM tag WHERE uuid = :tagId")
    suspend fun getTag(tagId : Int) : Tag

    @Query("DELETE FROM tag")
    suspend fun deleteAllTag()

    @Query("DELETE FROM tag WHERE uuid = :tagId")
    suspend fun deleteTag(tagId :Int)

    @Update
    suspend fun updateTag(tag : Tag)

}
