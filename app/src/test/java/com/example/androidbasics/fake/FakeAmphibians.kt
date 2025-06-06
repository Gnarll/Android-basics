package com.example.androidbasics.fake

import com.example.androidbasics.model.Amphibian

object FakeAmphibians {
    val amphibiansList: List<Amphibian> = listOf(
        Amphibian(name = "name1", type = "type1", description = "desc1", imgSrc = "img_src1"),
        Amphibian(name = "name2", type = "type2", description = "desc2", imgSrc = "img_src2"),
        Amphibian(name = "name3", type = "type3", description = "desc3", imgSrc = "img_src3")
    )
}