package com.example.androidbasics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @StringRes val name: Int,
    @DrawableRes val image: Int,
    val recommendedPlaces: List<RecommendedPlace>
)

data class RecommendedPlace(
    @StringRes val name: Int,
    @DrawableRes val image: Int,
    @StringRes val article: Int
)