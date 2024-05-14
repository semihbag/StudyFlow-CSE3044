package com.example.studyflow.interfaces.flashmind

import android.view.View

interface CardCreateBottomSheetClickListener {

    fun clickCardTitle(view: View)

    fun clickEditTextFront(view: View)

    fun clickImageFront(view: View)

    fun clickEditTextBack(view : View)

    fun clickImageBack(view: View)
    
}