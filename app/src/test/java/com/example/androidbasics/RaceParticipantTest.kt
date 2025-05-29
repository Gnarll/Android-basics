/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androidbasics

import com.example.androidbasics.ui.RaceParticipant
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test


class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Test",
        maxProgress = 100,
        progressDelayMillis = 500L,
        initialProgress = 0,
        progressIncrement = 1
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        launch {
            raceParticipant.run()
        }

        advanceTimeByAndRunPending(raceParticipant.progressDelayMillis)

        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        launch {
            raceParticipant.run()
        }

        advanceTimeByAndRunPending(raceParticipant.progressDelayMillis * raceParticipant.maxProgress)
        assertEquals(raceParticipant.maxProgress, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RacePaused_ProgressNotChanged() = runTest {
        val expectedProgress = 5

        val job = launch {
            raceParticipant.run()
        }


        advanceTimeByAndRunPending(raceParticipant.progressDelayMillis * expectedProgress)
        job.cancel()

        advanceTimeByAndRunPending(raceParticipant.progressDelayMillis * 2)

        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }


    @Test
    fun raceParticipant_RacePausedAndResumed_ProgressUpdated() = runTest {
        val progressPerIteration = 5
        val iterations = 2
        val expectedProgress = progressPerIteration * iterations

        repeat(iterations) {
            val job = launch {
                raceParticipant.run()
            }
            advanceTimeByAndRunPending(progressPerIteration * raceParticipant.progressDelayMillis)
            job.cancel()
        }


        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @Test(expected = IllegalArgumentException::class)
    fun raceParticipant_MaxProgressIsZero_ThrowsException() {
        RaceParticipant(name = "Progress Test", maxProgress = 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun raceParticipant_ProgressIncrementIsZero_ThrowsException() {
        RaceParticipant(name = "Progress Test", progressIncrement = 0)
    }


}
