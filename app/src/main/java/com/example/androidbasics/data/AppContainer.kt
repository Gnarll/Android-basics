package com.example.androidbasics.data

import com.example.androidbasics.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit =
        Retrofit.Builder().baseUrl("https://www.googleapis.com/books/v1/").addConverterFactory(
            json.asConverterFactory(contentType = "application/json".toMediaType())
        ).build()

    private val booksApiService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    override val booksRepository by lazy {
        DefaultBooksRepository(apiService = booksApiService)
    }
}