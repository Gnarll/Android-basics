package com.example.androidbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidbasics.ui.components.SleepBar
import com.example.androidbasics.ui.components.TimeGraph
import com.example.androidbasics.ui.theme.AndroidBasicsTheme
import com.example.androidbasics.ui.utils.timeGraphSleepBar


enum class WeekDays(val day: String) {
    Monday("Mon"),
    Tuesday("Tue"),
    Wednesday("Wed"),
    Thursday("Thu"),
    Friday("Fri"),
    Saturday("Sat"),
    Sunday("Sun"),
}


class SleepData(val day: WeekDays, val goToBedTime: Int, val getUpTime: Int) {
    fun calculateDuration(): Int {
        val sleepDurationUncertainty = getUpTime - goToBedTime
        return if (sleepDurationUncertainty < 0) 24 + sleepDurationUncertainty else sleepDurationUncertainty
    }
}

const val MAX_HOURS_INTERVAL = 7
const val FIRST_HOUR = 20

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBasicsTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->


                    val mockSleepData: List<SleepData> =
                        listOf(
                            SleepData(day = WeekDays.Monday, goToBedTime = 20, getUpTime = 2),
                            SleepData(day = WeekDays.Tuesday, goToBedTime = 21, getUpTime = 1),
                            SleepData(day = WeekDays.Wednesday, goToBedTime = 0, getUpTime = 2),
                            SleepData(day = WeekDays.Thursday, goToBedTime = 22, getUpTime = 23),
                            SleepData(day = WeekDays.Friday, goToBedTime = 20, getUpTime = 23),
                            SleepData(day = WeekDays.Saturday, goToBedTime = 21, getUpTime = 2),
                            SleepData(day = WeekDays.Sunday, goToBedTime = 20, getUpTime = 1),
                        )

                    val hours: List<Int> = listOf(
                        20,
                        21,
                        22,
                        23,
                        0,
                        1,
                        2,
                    )

                    TimeGraph(
                        hoursHeader = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(vertical = 14.dp)
                                    .background(
                                        color = Color.Yellow,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            ) {
                                hours.forEach {
                                    Text(
                                        text = it.toString(),
                                        modifier = Modifier
                                            .width(50.dp)
                                    )
                                }
                            }
                        },
                        dayLabel = { day ->
                            Text(
                                text = day.day,
                                modifier = Modifier
                                    .height(24.dp)
                            )
                        },
                        sleepBar = { data ->
                            SleepBar(
                                modifier = Modifier
                                    .timeGraphSleepBar(data)
                                    .padding(bottom = 16.dp)
                                    .height(24.dp)
                            )
                        },
                        sleepData = mockSleepData,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}
