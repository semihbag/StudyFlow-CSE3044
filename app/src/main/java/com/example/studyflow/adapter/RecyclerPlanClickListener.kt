package com.example.studyflow.adapter

import android.view.View

interface RecyclerPlanClickListener {

    fun rowClick(view: View);
    fun checkBoxClick(view: View);

}