package com.example.androidbasics.ui.utils

import androidx.compose.ui.Modifier
import com.example.androidbasics.FIRST_HOUR
import com.example.androidbasics.SleepData
import com.example.androidbasics.ui.components.TimeGraphParentData

fun Modifier.timeGraphSleepBar(sleepData: SleepData) = this.then(
    TimeGraphParentData(
        duration = sleepData.calculateDuration(),
        skipHours = (sleepData.goToBedTime - FIRST_HOUR).run {
            return@run if (this < 0) this + 24 else this
        }
    )
)