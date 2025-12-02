package com.example.androidbasics.cases

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

// Returning to simple class, making it unstable by defining var param
// We also can avoid Child recomposition by saving this object across recompositions
// using remember -> Child doesn't recompose

class MyUiState4(var name: String)


@Composable
fun Parent4() {
    SideEffect {
        println("recomposed Parent4 ")
    }

    // saving this object assures that compose comparing it
    // will get the same object
    val myState = remember {
        MyUiState4("Name")
    }

    var stateForRecomposition by remember { mutableStateOf("Not important") }
    val dummy = stateForRecomposition

    Button(onClick = {
        stateForRecomposition += "."
    }) {
        Text("Recompose Parent4")
    }
    Child4(
        text = "text", myState, modifier = Modifier
    )
}

@Composable
fun Child4(text: String, stateParam: MyUiState4, modifier: Modifier = Modifier) {
    SideEffect {
        println("recomposed Child4 $stateParam")
    }

    Text(text = text, modifier = modifier)
}

