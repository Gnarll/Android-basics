package com.example.androidbasics.cases

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Stable
data class UiState(val list: List<Int>, var i: Int = 1)

@Composable
fun Parent6() {
    SideEffect { println("Parent recomposed ") }

    var counter by remember { mutableIntStateOf(1) }
    val dummy = counter

    val capturedUiState = UiState(listOf(1, 3, 5))

    Button(onClick = { counter += 1 }) {
        Text("Click")
    }

    // lambda implicitly remembers captured values with remember(capturedValue)
    // comparing capturedValue as usual (Stable by equals, unstable by link)
    Child6(lambda = { println(capturedUiState) })
}

@Composable
fun Child6(lambda: () -> Unit) {
    SideEffect { println("Child recomposed") }

    lambda()
    Text("Child")
}

