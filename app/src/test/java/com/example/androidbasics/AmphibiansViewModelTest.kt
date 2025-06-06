package com.example.androidbasics

import com.example.androidbasics.fake.FakeAmphibians
import com.example.androidbasics.fake.FakeAmphibiansRepository
import com.example.androidbasics.rules.DispatchersRule
import com.example.androidbasics.ui.AmphibianUiState
import com.example.androidbasics.ui.AmphibiansViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class AmphibiansViewModelTest {

    @get:Rule
    val dispatchersRule = DispatchersRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun amphibiansViewModel_getAmphibians_VerifySuccess() = runTest {
        val viewModel = AmphibiansViewModel(amphibiansRepository = FakeAmphibiansRepository())
        advanceUntilIdle()

        val amphibians = viewModel.uiState.value

        assertEquals(
            amphibians, AmphibianUiState.Success(FakeAmphibians.amphibiansList)
        )


    }

}