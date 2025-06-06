package com.example.androidbasics.fake

import com.example.androidbasics.model.Amphibian
import com.example.androidbasics.network.AmphibianApiService

class FakeAmphibianApiService : AmphibianApiService {
    override suspend fun fetchAmphibians(): List<Amphibian> {
        return listOf(
            Amphibian(name = "name1", type = "type1", description = "desc1", imgSrc = "img_src1"),
            Amphibian(name = "name2", type = "type2", description = "desc2", imgSrc = "img_src2"),
            Amphibian(name = "name3", type = "type3", description = "desc3", imgSrc = "img_src3")
        )
    }
}