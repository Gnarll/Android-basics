package com.example.androidbasics.network

import retrofit2.http.GET


interface MarsApiService {
    @GET("photos")
    suspend fun fetchPhotos(): List<MarsPhoto>
}


