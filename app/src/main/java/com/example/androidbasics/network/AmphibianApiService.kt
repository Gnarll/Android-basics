package com.example.androidbasics.network

import com.example.androidbasics.model.Amphibian
import retrofit2.http.GET


interface AmphibianApiService {
    @GET(value = "amphibians")
    suspend fun fetchAmphibians(): List<Amphibian>
}

