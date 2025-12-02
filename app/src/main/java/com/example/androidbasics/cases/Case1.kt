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


// Here we see that despite having a mutableStateOf call in Parent1,
// it doesn't actually recompose when clicking button because Parent1
// doesn't read it, so only button is recomposed


// now it doesn't matter
class MyUiState1(val name: String)

@Composable
fun Parent1() {
    val myState = MyUiState1("Name")
    SideEffect {
        println("recomposed Parent1 ")
    }

    var stateForRecomposition by remember { mutableStateOf("Not important") }

//    val dummy = stateForRecomposition

    Button(onClick = {
        stateForRecomposition += "."
    }) { Text("Recompose Parent1") }

    Child1(
        text = "text", myState, modifier = Modifier
    )
}

@Composable
fun Child1(text: String, stateParam: MyUiState1, modifier: Modifier = Modifier) {
    SideEffect {
        println("recomposed Child1 $stateParam")
    }

    Text(text = text, modifier = modifier)
}

