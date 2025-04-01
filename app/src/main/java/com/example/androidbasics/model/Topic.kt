package com.example.androidbasics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleStringId: Int,
    val topicId: Int,
    @DrawableRes val imageId: Int
)