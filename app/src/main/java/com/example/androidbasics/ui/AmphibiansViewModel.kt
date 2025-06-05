package com.example.androidbasics.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidbasics.MainApplication
import com.example.androidbasics.data.AmphibiansRepository
import com.example.androidbasics.model.Amphibian
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed class AmphibianUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibianUiState()
    object Loading : AmphibianUiState()
    object Error : AmphibianUiState()
}

class AmphibiansViewModel(val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    private var _uiState: MutableStateFlow<AmphibianUiState> =
        MutableStateFlow(AmphibianUiState.Loading)
    val uiState: StateFlow<AmphibianUiState> = _uiState.asStateFlow()

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            val resultList =
                try {
                    AmphibianUiState.Success(amphibiansRepository.getAmphibians())

                } catch (e: Throwable) {
                    AmphibianUiState.Error
                }

            _uiState.update {
                resultList
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as MainApplication
                AmphibiansViewModel(amphibiansRepository = app.appContainer.amphibiansRepository)
            }
        }
    }
}