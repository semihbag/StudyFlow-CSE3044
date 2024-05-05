package com.example.studyflow.interfaces.tag

import android.view.View

open interface TagFragmentClickListener {

    fun clickAddTag(view: View)

    fun clickDeleteTag(view: View)
}