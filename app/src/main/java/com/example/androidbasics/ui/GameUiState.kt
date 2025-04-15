package com.example.androidbasics.ui


data class GameUiState(
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 1,
    val currentScrambledWord: String = "",
    val isGameOver: Boolean = false,
) {

}

