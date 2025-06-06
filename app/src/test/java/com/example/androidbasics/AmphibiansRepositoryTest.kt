package com.example.androidbasics

import com.example.androidbasics.data.AmphibiansRepositoryImpl
import com.example.androidbasics.fake.FakeAmphibianApiService
import com.example.androidbasics.fake.FakeAmphibians
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class AmphibiansRepositoryTest {
    @Test
    fun amphibiansRepository_fetchAmphibians_verifySuccess() = runTest {
        val repository = AmphibiansRepositoryImpl(FakeAmphibianApiService())
        val amphibiansList = repository.getAmphibians()

        assertEquals(amphibiansList, FakeAmphibians.amphibiansList)
    }
}