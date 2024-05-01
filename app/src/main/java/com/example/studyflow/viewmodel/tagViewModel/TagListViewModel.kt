package com.example.studyflow.viewmodel.tagViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyflow.model.Tag

class TagListViewModel : ViewModel() {
    val tags = MutableLiveData<List<Tag>>()

    fun getTagsFromDB() {
        val tag1 = Tag("bababab",100,100,250,20,81,25)
        val tag2 = Tag("data2",200,200,500,20,81,50)
        val tag3 = Tag("data3",300,300,750,20,81,75)

        val tagList = arrayListOf<Tag>(tag1,tag2,tag3)
        tags.value = tagList
    }
}