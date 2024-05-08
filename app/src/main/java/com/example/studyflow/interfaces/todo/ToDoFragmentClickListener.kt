package com.example.studyflow.interfaces.todo

import android.view.View

interface ToDoFragmentClickListener {
    fun clickAddToDo(view: View)

    fun clickShowTagList(view: View)

    fun clickSelectTag(view: View)
}