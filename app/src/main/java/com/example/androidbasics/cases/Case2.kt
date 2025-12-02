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


// Now we read stateForRecomposition in Parent, therefore we see recomposition of Parent
// itself and now as the Parent recomposes we are interested in Child, cause it begins to
// think about recomposition too.

// class fields are immutable thus inferred as immutable
// compared by equals
class MyUiState2(val name: String) {
    // either implement this or write data class
    override fun equals(other: Any?): Boolean = when (other) {
        !is MyUiState2 -> false
        else -> other.name == name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
    // we could write in this way instead
    //data class MyUiState2(val name: String)
}

@Composable
fun Parent2() {
    SideEffect {
        println("recomposed Parent2 ")
    }

    val myState = MyUiState2("Name")

    var stateForRecomposition by remember { mutableStateOf("Not important") }
    val dummy = stateForRecomposition

    Button(onClick = {
        stateForRecomposition += "."
    }) {
        Text("Recompose Parent2")
    }
    Child2(
        text = "text", myState, modifier = Modifier
    )
}

@Composable
fun Child2(text: String, stateParam: MyUiState2, modifier: Modifier = Modifier) {
    SideEffect {
        // if we remove stateParam from here, that is
        // stop reading it in Child even accepting it,
        // Child won't be recomposed
        println("recomposed Child2 $stateParam")
    }

    Text(text = text, modifier = modifier)
}

