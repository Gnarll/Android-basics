package com.example.androidbasics.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbasics.ui.sampleNotes


@Composable
fun NoteListScreen(onItemClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(sampleNotes, key = { it.id }) { note ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = note.color)
                    .clickable {
                        onItemClick(note.id)
                    }) {
                Text(
                    text = note.title,
                    fontSize = 18.sp
                )
                Text(text = note.content)

            }
        }
    }
}