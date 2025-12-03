package com.example.androidbasics.cases

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


// The best case for beneficial use of @Immutable I could come up with
// is creating this object without remember and let framework compare it
// by equals instead of link, as it becomes when we have unstable types
@Immutable
data class UserProfile(
    val id: String,
    val name: String,
    val hobbies: List<String> = listOf("whatever")
)


@Composable
fun Parent5() {
    SideEffect { println("Parent recomposed ") }

    var counter by remember { mutableIntStateOf(1) }
    val dummy = counter

    val buttonLambda = { counter += 1 }

    Button(onClick = buttonLambda) {
        Text("Click")
    }

    // simple remember could reduce recompositions, caused by parent recomposition
    Child5(userProfile = UserProfile("1", "El Born"))

}

@Composable
fun Child5(userProfile: UserProfile) {
    SideEffect { println("Child recomposed") }

    Text("$userProfile")
}

