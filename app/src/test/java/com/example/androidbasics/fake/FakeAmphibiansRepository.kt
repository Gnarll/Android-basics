package com.example.androidbasics.fake

import com.example.androidbasics.data.AmphibiansRepository
import com.example.androidbasics.model.Amphibian

class FakeAmphibiansRepository : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeAmphibians.amphibiansList
    }

}