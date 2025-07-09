package com.example.androidbasics.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteDetailViewModel(private val noteId: Int) : ViewModel() {


    private var _uiState = MutableStateFlow(
        sampleNotes.first {
            it.id == noteId
        }
    )
    val uiState: StateFlow<Note> = _uiState.asStateFlow()
}