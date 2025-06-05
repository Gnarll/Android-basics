package com.example.androidbasics.data

import com.example.androidbasics.model.Amphibian
import com.example.androidbasics.network.AmphibianApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class AmphibiansRepositoryImpl(val amphibianApiService: AmphibianApiService) :
    AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibianApiService.fetchAmphibians()
    }
}