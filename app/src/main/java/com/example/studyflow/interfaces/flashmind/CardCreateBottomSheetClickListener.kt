package com.example.studyflow.interfaces.flashmind

import android.view.View

interface CardCreateBottomSheetClickListener {

    fun clickCardTitle(v: View)

    fun clickEditTextFront(v: View)

    fun clickImageFront(v: View)

    fun clickEditTextBack(v : View)

    fun clickImageBack(v: View)
    
}