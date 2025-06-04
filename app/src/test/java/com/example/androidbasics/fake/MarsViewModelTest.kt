package com.example.androidbasics.fake

import com.example.androidbasics.rules.TestDispatcherRule
import com.example.androidbasics.ui.screens.MarsUiState
import com.example.androidbasics.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Test
    fun marsViewModel_getPhotos_verifyMarsUiStateSuccess() =
        runTest {

            val marsViewModel =
                MarsViewModel(marsPhotosRepository = FakeNetworkMarsPhotosRepository())
            assertEquals(MarsUiState.Success(FakeDataSource.photosList), marsViewModel.marsUiState)

        }
}