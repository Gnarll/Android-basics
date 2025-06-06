package com.example.androidbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.androidbasics.model.Book
import com.example.androidbasics.ui.theme.AndroidBasicsTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBasicsTheme {


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Test()
                }
            }
        }
    }
}


@Composable
fun Test(modifier: Modifier = Modifier) {
    val app = LocalContext.current.applicationContext as MainApplication
    var books: List<Book>

    runBlocking {

        books = app.appContainer.booksRepository.getBooks("E5V4zfHYaw0C")
    }
    Text(text = "${books.size}")
}