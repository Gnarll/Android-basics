package com.example.androidbasics.ui

import androidx.lifecycle.ViewModel
import com.example.androidbasics.data.Datasource
import com.example.androidbasics.data.DessertUiState
import com.example.androidbasics.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel(val desserts: List<Dessert> = Datasource.dessertList) : ViewModel() {
    private var _uiState: MutableStateFlow<DessertUiState> = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentState ->
            with(currentState) {
                this.copy(
                    dessertsSold = dessertsSold.inc(),
                    totalRevenue = totalRevenue.plus(desserts[currentDessertIndex].price)
                )
            }
        }

        updateDessert()
    }

    private fun updateDessert() {
        val nextDessertIndex = _uiState.value.currentDessertIndex + 1
        val nextDessert: Dessert? = desserts.elementAtOrNull(nextDessertIndex)

        if (nextDessert == null) {
            return
        }

        if (nextDessert.startProductionAmount == _uiState.value.dessertsSold) {
            with(nextDessert) {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentDessertIndex = nextDessertIndex,
                        currentDessertPrice = price,
                        currentDessertImageId = imageId
                    )
                }
            }
        }
    }


}