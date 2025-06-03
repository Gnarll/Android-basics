package com.example.androidbasics.data

import com.example.androidbasics.network.MarsApi
import com.example.androidbasics.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return MarsApi.retrofitService.getPhotos()
    }
}