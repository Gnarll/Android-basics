package com.example.androidbasics.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class DessertViewModelTest {
    private val viewModel = DessertViewModel()

    @Test
    fun dessertViewModel_OneDessertSold_RevenueAndSoldValueUpdated() {
        var currentDessertState = viewModel.uiState.value

        viewModel.onDessertClicked()

        currentDessertState = viewModel.uiState.value

        assertEquals(currentDessertState.dessertsSold, 1)
        assertEquals(currentDessertState.totalRevenue, currentDessertState.currentDessertPrice)

    }

    @Test
    fun dessertViewModel_DessertSoldWithUpdate_DessertAndValuesUpdated() {
        var currentDessertState = viewModel.uiState.value
        val nextDessert = viewModel.desserts[currentDessertState.currentDessertIndex + 1]

        val itersUntilNext = nextDessert.startProductionAmount
        val expectedRevenue = itersUntilNext * currentDessertState.currentDessertPrice

        repeat(itersUntilNext) {
            viewModel.onDessertClicked()
        }

        currentDessertState = viewModel.uiState.value

        assertEquals(currentDessertState.dessertsSold, itersUntilNext)
        assertEquals(currentDessertState.currentDessertPrice, nextDessert.price)
        assertEquals(currentDessertState.totalRevenue, expectedRevenue)
    }

    @Test
    fun dessertViewModel_Initialization_DesertAndValuesAreInitial() {

        val currentDessertState = viewModel.uiState.value

        assertEquals(currentDessertState.dessertsSold, 0)
        assertEquals(currentDessertState.currentDessertIndex, 0)
        assertEquals(currentDessertState.totalRevenue, 0)
    }


}