package com.example.studyflow.interfaces.todo

import android.view.View

interface ToDoFragmentClickListener {
    fun addToDo(view: View)

    fun showTagList(view: View)
}