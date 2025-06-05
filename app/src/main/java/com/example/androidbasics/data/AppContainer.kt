package com.example.androidbasics.data

import com.example.androidbasics.network.AmphibianApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val amphibiansRepository: AmphibiansRepository
}

class DefaultAppContainer : AppContainer {
    private val apiBaseUrl = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit =
        Retrofit.Builder().baseUrl(apiBaseUrl).addConverterFactory(MoshiConverterFactory.create())
            .build()

    val retrofitService: AmphibianApiService by lazy {
        retrofit.create<AmphibianApiService>(AmphibianApiService::class.java)
    }

    override val amphibiansRepository by lazy {
        AmphibiansRepositoryImpl(amphibianApiService = retrofitService)
    }
}