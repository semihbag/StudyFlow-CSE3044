package com.example.studyflow.util

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.convertStringToUri(uriString: String?) {
    uriString?.let {
        Glide.with(this)
            .load(Uri.parse(it))
            .into(this)
    }
}
