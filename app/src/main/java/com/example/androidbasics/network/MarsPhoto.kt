package com.example.androidbasics.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)  // param names must either correspond receiving JSON original names or
// have @SerialName annotation wrapping this name