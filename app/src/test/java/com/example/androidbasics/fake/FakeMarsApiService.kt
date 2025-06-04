package com.example.androidbasics.fake

import com.example.androidbasics.network.MarsApiService
import com.example.androidbasics.network.MarsPhoto

class FakeMarsApiService : MarsApiService {
    override suspend fun fetchPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}