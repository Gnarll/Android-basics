package com.example.androidbasics.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidbasics.MainApplication
import com.example.androidbasics.data.BooksRepository
import com.example.androidbasics.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class UiState(
    val booksUiState: BooksUiState = BooksUiState.Loading(),
    val searchValue: String = ""
)

sealed interface BooksUiState {
    class Success(val books: List<Book>) : BooksUiState
    class Loading : BooksUiState
    class Error : BooksUiState
}

class BooksViewModel(val booksRepository: BooksRepository) : ViewModel() {
    private var _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onChangeSearchValue(value: String) {
        _uiState.update {
            it.copy(searchValue = value)
        }
    }

    fun onSearch() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    booksUiState = try {
                        val books = booksRepository.getBooks(query = _uiState.value.searchValue)

                        BooksUiState.Success(books)
                    } catch (e: Throwable) {
                        BooksUiState.Error()
                    }
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MainApplication
                val booksRepository = application.appContainer.booksRepository

                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}