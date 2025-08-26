package com.example.androidbasics.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import com.example.androidbasics.MAX_HOURS_INTERVAL
import com.example.androidbasics.SleepData
import com.example.androidbasics.WeekDays


@Composable
fun TimeGraph(
    hoursHeader: @Composable () -> Unit,
    sleepData: List<SleepData>,
    dayLabel: @Composable (weekday: WeekDays) -> Unit,
    sleepBar: @Composable (sleepData: SleepData) -> Unit,
    modifier: Modifier = Modifier
) {
    val dayLabels = @Composable {
        sleepData.forEach { dayLabel(it.day) }
    }
    val sleepBars = @Composable {
        sleepData.forEach {
            sleepBar(it)
        }
    }

    Layout(
        contents = listOf(
            hoursHeader,
            dayLabels,
            sleepBars
        ) as List<@Composable @UiComposable (() -> Unit)>,
        modifier = modifier
    ) { (hoursHeaderMeasurables, dayLabelsMeasurables, sleepBarsMeasurables), constraints ->

        val hoursHeaderPlaceable = hoursHeaderMeasurables.first().measure(constraints)
        val dayLabelsPlaceables = dayLabelsMeasurables.map { measurable ->
            measurable.measure(constraints)
        }

        val maxDayLabelWidth = dayLabelsPlaceables.maxBy { it.width }.width
        val sleepBarOffsets = sleepBarsMeasurables.map { measurable ->
            val barParentData = measurable.parentData as TimeGraphParentData
            val barOffset =
                hoursHeaderPlaceable.width / MAX_HOURS_INTERVAL * barParentData.skipHours

            barOffset
        }

        var totalHeight = hoursHeaderPlaceable.height

        val sleepBarPlaceables = sleepBarsMeasurables.map { measurable ->
            val barParentData = measurable.parentData as TimeGraphParentData
            val barWidth = hoursHeaderPlaceable.width / MAX_HOURS_INTERVAL * barParentData.duration


            val barPlaceable =
                measurable.measure(
                    constraints.copy(
                        minWidth = barWidth,
                        maxWidth = barWidth
                    )
                )

            totalHeight += barPlaceable.height
            barPlaceable
        }


        val totalWidth = maxDayLabelWidth + hoursHeaderPlaceable.width

        layout(width = totalWidth, height = totalHeight) {
            val xPosition = maxDayLabelWidth
            var yPosition = hoursHeaderPlaceable.height

            hoursHeaderPlaceable.place(xPosition, 0)


            sleepBarPlaceables.forEachIndexed { sleepBarPlaceableIndex, sleepBarPlaceable ->
                val currentOffset = sleepBarOffsets[sleepBarPlaceableIndex]
                sleepBarPlaceable.place(xPosition + currentOffset, yPosition)

                val correspondingDayLabelPlaceable = dayLabelsPlaceables[sleepBarPlaceableIndex]

                correspondingDayLabelPlaceable.place(
                    0,
                    yPosition
                )

                yPosition += sleepBarPlaceable.height
            }
        }
    }
}


class TimeGraphParentData(val skipHours: Int, val duration: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? =
        this@TimeGraphParentData
}