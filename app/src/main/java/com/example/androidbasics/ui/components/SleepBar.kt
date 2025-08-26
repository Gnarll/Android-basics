package com.example.androidbasics.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SleepBar(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color.Yellow, shape = RoundedCornerShape(15.dp)))
}

@Preview
@Composable
fun SleepBarPreview(modifier: Modifier = Modifier) {
    SleepBar(
        modifier = Modifier
            .width(70.dp)
            .height(30.dp)
    )
}