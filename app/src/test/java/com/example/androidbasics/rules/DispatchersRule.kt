package com.example.androidbasics.rules

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class DispatchersRule @OptIn(ExperimentalCoroutinesApi::class)
constructor(val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()) :
    TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
    }

    override fun finished(description: Description?) {
        super.finished(description)
    }
}