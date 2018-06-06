package com.alextroy.news22.utils

import android.app.Activity
import android.widget.Toast
import com.alextroy.news22.BuildConfig

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

const val BASE_URL = "http://content.guardianapis.com/?&show-fields=thumbnail,trailText/"
const val TRAIL_TEXT: String = "thumbnail,trailText"
const val KEY: String = BuildConfig.NEWS_API_KEY