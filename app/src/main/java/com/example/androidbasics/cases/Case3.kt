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

// Here we've added the List<Int>, which actually tell us that now class is not stable
// so Child will be recomposed, but if we manually add @Stable or @Immutable annotation
// it won't (It could work even if we had var params instead of val)
data class MyUiState3(val name: String, val list: List<Int>)


@Composable
fun Parent3() {
    SideEffect {
        println("recomposed Parent3 ")
    }

    val myState = MyUiState3("Name", listOf(1, 2, 3))

    var stateForRecomposition by remember { mutableStateOf("Not important") }
    val dummy = stateForRecomposition

    Button(onClick = {
        stateForRecomposition += "."
    }) {
        Text("Recompose Parent3")
    }
    Child3(
        text = "text", myState, modifier = Modifier
    )
}

@Composable
fun Child3(text: String, stateParam: MyUiState3, modifier: Modifier = Modifier) {
    SideEffect {
        println("recomposed Child3 $stateParam")
    }

    Text(text = text, modifier = modifier)
}

