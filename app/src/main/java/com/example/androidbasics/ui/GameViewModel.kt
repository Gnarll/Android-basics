package com.example.androidbasics.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidbasics.data.MAX_NO_OF_WORDS
import com.example.androidbasics.data.SCORE_INCREASE
import com.example.androidbasics.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class GameViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set


    private lateinit var currentWord: String
    private val usedWords: MutableSet<String> = mutableSetOf()

    init {
        resetGame()
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }

        updateUserGuess("")
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

    private fun shuffleWord(word: String): String {
        var tempWord = word.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }

        return String(tempWord)
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()

        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleWord(currentWord)
        }
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    isGameOver = true,
                    isGuessedWordWrong = false
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    isGuessedWordWrong = false,
                    currentWordCount = currentState.currentWordCount.inc()
                )
            }
        }
    }
}