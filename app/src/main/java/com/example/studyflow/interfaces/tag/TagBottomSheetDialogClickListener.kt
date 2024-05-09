package com.example.studyflow.interfaces.tag

import android.view.View
import androidx.navigation.Navigation
import com.example.studyflow.view.HomePageFragmentDirections

interface TagBottomSheetDialogClickListener {

    fun clickSelectTag(view : View)

    fun clickAddTagFromTagBottomSheetDialog(view: View)
}