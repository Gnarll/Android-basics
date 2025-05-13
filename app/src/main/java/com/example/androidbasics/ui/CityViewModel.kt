package com.example.androidbasics.ui

import androidx.lifecycle.ViewModel
import com.example.androidbasics.model.Category
import com.example.androidbasics.model.RecommendedPlace
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {
    private val _cityUiState = MutableStateFlow(CityUiState())
    val cityUiState: StateFlow<CityUiState> = _cityUiState.asStateFlow()

    fun setCurrentCategory(category: Category) {
        _cityUiState.update { currentState ->
            currentState.copy(currentCategory = category)
        }
    }

    fun setCurrentRecommendedPlace(place: RecommendedPlace) {
        _cityUiState.update { currentState ->
            currentState.copy(currentRecommendedPlace = place)
        }
    }

}