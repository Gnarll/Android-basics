package com.example.androidbasics.network

import com.example.androidbasics.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApiService {
    @GET("volumes")
    suspend fun fetchBooks(@Query("q") query: String): BooksResponse
}