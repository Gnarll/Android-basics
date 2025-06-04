package com.example.androidbasics.fake

import com.example.androidbasics.network.MarsPhoto

object FakeDataSource {
    val photosList: List<MarsPhoto> = listOf(
        MarsPhoto(id = "1", imgSrc = "url-one"),
        MarsPhoto(id = "2", imgSrc = "url-two"),
        MarsPhoto(id = "3", imgSrc = "url-three")
    )
}