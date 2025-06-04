package com.example.androidbasics.fake

import com.example.androidbasics.data.MarsPhotosRepository
import com.example.androidbasics.network.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}