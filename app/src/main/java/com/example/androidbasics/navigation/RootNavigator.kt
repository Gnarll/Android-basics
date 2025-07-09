package com.example.androidbasics.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.SinglePaneSceneStrategy
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.androidbasics.ui.screens.NoteDetailScreen
import com.example.androidbasics.ui.screens.NoteListScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Serializable
data object NoteListScreen : NavKey

@Serializable
data class NoteDetailScreen(val id: Int) : NavKey

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun RootNavigator(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(NoteListScreen)
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        sceneStrategy = SinglePaneSceneStrategy(),
        entryProvider = { key ->
            when (key) {
                is NoteListScreen -> {
                    NavEntry(key = key) {
                        NoteListScreen(onItemClick = { id ->
                            backStack.addLast(NoteDetailScreen(id = id))
                        }, modifier = modifier)
                    }
                }

                is NoteDetailScreen -> {
                    NavEntry(key = key) {
                        NoteDetailScreen(
                            viewModel = koinViewModel { parametersOf(key.id) },
                            modifier = modifier
                        )
                    }
                }

                else -> {
                    throw RuntimeException("Invalid Entry key")
                }
            }
        }
    )
}
