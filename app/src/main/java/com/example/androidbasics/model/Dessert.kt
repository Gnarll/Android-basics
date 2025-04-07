package com.example.androidbasics.model

import androidx.annotation.DrawableRes

data class Dessert(@DrawableRes val imageId: Int, val price: Int, val startProductionAmount: Int)
