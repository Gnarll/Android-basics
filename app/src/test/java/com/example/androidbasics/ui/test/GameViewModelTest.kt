package com.example.androidbasics.ui.test

import com.example.androidbasics.data.MAX_NO_OF_WORDS
import com.example.androidbasics.data.SCORE_INCREASE
import com.example.androidbasics.data.getUnscrambledWord
import com.example.androidbasics.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()  // recreates when new method in stack is called

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER: Int = SCORE_INCREASE
    }

    // Success case
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        var currentGameState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameState = viewModel.uiState.value

        assertFalse(currentGameState.isGuessedWordWrong)
        assertEquals(currentGameState.score, SCORE_AFTER_FIRST_CORRECT_ANSWER)


    }

    // Error case
    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        var currentGameState = viewModel.uiState.value
        val lastGameScore = currentGameState.score
        val wrongPlayerWord = currentGameState.currentScrambledWord // guaranteed wrong answer

        viewModel.updateUserGuess(wrongPlayerWord)
        viewModel.checkUserGuess()

        currentGameState = viewModel.uiState.value

        assertTrue(currentGameState.isGuessedWordWrong)
        assertEquals(lastGameScore, currentGameState.score)
    }

    // Boundary Case (the beginning of the game)
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val currentGameState = viewModel.uiState.value
        val currentUnscrambledWord = getUnscrambledWord(currentGameState.currentScrambledWord)

        assertFalse(currentGameState.isGuessedWordWrong)
        assertFalse(currentGameState.isGameOver)

        assertEquals(currentGameState.score, 0)
        assertEquals(currentGameState.currentWordCount, 1)

        assertNotEquals(currentGameState.currentScrambledWord, currentUnscrambledWord)

    }

    // Boundary Case (the end of the game)
    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var currentGameState = viewModel.uiState.value
        var expectedScore = 0


        repeat(MAX_NO_OF_WORDS) {
            var unscrambledWord = getUnscrambledWord(currentGameState.currentScrambledWord)
            viewModel.updateUserGuess(unscrambledWord)
            viewModel.checkUserGuess()
            expectedScore += SCORE_INCREASE

            currentGameState = viewModel.uiState.value
            assertEquals(currentGameState.score, expectedScore)
        }

        assertEquals(currentGameState.score, expectedScore)
        assertEquals(currentGameState.currentWordCount, MAX_NO_OF_WORDS)
        assertTrue(currentGameState.isGameOver)
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentGameState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameState = viewModel.uiState.value
        val lastWordCount = currentGameState.currentWordCount

        viewModel.skipWord()

        currentGameState = viewModel.uiState.value
        assertEquals(lastWordCount + 1, currentGameState.currentWordCount)
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameState.score)
    }

}