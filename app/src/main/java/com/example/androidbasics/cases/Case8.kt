package com.example.androidbasics.cases

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


//unstable
data class UiState8(var str: String)

@Composable
fun Parent8() {


    var count by remember { mutableIntStateOf(0) }
    val read = count // parent recomposition

    Button(onClick = { count++ }) {
        Text("Recompose")
    }

    val unstableState = UiState8("String")

    // won't be printed as it's compared by equals
    val test = remember(unstableState) {
        println("RE-remember")
        "nevermind"
    }

    // compose remembers lambdas implicitly using captured
    // values as keys but unlike usual remember, which compares keys by equals(),
    // this compares keys depending on their stability (by equals() for stable, by link for unstable)
    val lambda = {
        unstableState.str
    }

    Child8(textProvider = lambda)
}

@Composable
fun Child8(modifier: Modifier = Modifier, textProvider: () -> String) {
    // will be printed
    println("recomposition")
    Text(textProvider())
}
