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
    val booksUiState: BooksUiState = BooksUiState.Idle,
    val searchValue: String = "",
    val isLoadingMore: Boolean = false
)

sealed class BooksUiState {
    data class Success(val books: List<Book>) : BooksUiState()
    object Loading : BooksUiState()
    object Error : BooksUiState()
    object Idle : BooksUiState()
}

class BooksViewModel(val booksRepository: BooksRepository) : ViewModel() {
    private var _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onChangeSearchValue(value: String) {
        _uiState.update {
            it.copy(searchValue = value.trim())
        }
    }

    fun onSearch() {
        viewModelScope.launch {
            booksRepository.resetPagination()
            fetchBooks(shouldAppend = false)
        }
    }

    fun loadMoreBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMore = true) }
            try {
                fetchBooks(shouldAppend = true)
            } finally {
                _uiState.update { it.copy(isLoadingMore = false) }
            }
        }
    }

    private suspend fun fetchBooks(shouldAppend: Boolean) {
        if (shouldAppend) {
            _uiState.update { it.copy(isLoadingMore = true) }
        } else {
            _uiState.update { it.copy(booksUiState = BooksUiState.Loading) }
        }

        val currentBooks = (_uiState.value.booksUiState as? BooksUiState.Success)?.books.orEmpty()

        val bookUiState = try {
            val newBooks = booksRepository.fetchNextPage(query = _uiState.value.searchValue)
            val combinedBooks = if (shouldAppend) currentBooks + newBooks else newBooks
            BooksUiState.Success(combinedBooks)
        } catch (e: Throwable) {
            if (shouldAppend) BooksUiState.Success(currentBooks) else BooksUiState.Error
        }

        _uiState.update {
            it.copy(
                booksUiState = bookUiState,
                isLoadingMore = false
            )
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