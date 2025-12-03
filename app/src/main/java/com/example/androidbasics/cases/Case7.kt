package com.example.androidbasics.cases

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout

@Stable
data class UiState7(
    val state1: MutableState<Int>,
    val state2: MutableState<String>,
    var counter: Int = 0
)

@Composable
fun Parent7() {
    SideEffect { println("Parent recomposed ") }


    val UiState7 = remember {
        UiState7(
            mutableIntStateOf(1),
            mutableStateOf("init"),
            0
        )
    }

    val buttonLambda = { UiState7.state1.value += 1 }

    Button(
        onClick = buttonLambda
    ) {
        Text("Click")

    }


    Child7(
        uiState = UiState7.state1, modifier = Modifier
            .layout { measurable, constraints ->
                // reading state from layout phase
                //val read = UiState7.state1.value
                println("Child's measuring phase")
                val placeable = measurable.measure(constraints)
                layout(constraints.minWidth, constraints.minHeight) {
                    placeable.placeRelative(0, 0)
                }
            }
            .graphicsLayer {
                // reading state from drawing phase
                //val read = UiState7.state1.value
                println("Child's drawing phase")
            })

}

data class UnstableList(val list: List<Int> = listOf(1, 2, 3))

@Composable
fun Child7(modifier: Modifier = Modifier, uiState: State<Int>) {
    Column(modifier) {
        // Reading state from composition phase
        // only this phase potentially triggers children's recomposition
        // so it's better to transport reading to them if possible
        val read = uiState.value

        SideEffect {
            println("Child recomposed")
        }
        val grandchildState = UnstableList()

        Text("Child")
        Grandchild(state = grandchildState)
    }
}

@Composable
fun Grandchild(state: UnstableList) {
    Text("grandchild ${state.list.size}")
}