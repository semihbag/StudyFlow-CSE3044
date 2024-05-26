package com.example.studyflow.util

import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.Date
import java.util.Locale

fun ImageView.convertStringToUri(uriString: String?) {
    uriString?.let {
        Glide.with(this)
            .load(Uri.parse(it))
            .into(this)
    }
}
