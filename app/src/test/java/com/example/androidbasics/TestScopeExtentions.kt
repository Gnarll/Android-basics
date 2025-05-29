package com.example.androidbasics

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent


fun TestScope.advanceTimeByAndRunPending(delayTimeMillis: Long) {
    advanceTimeBy(delayTimeMillis)
    runCurrent()
}