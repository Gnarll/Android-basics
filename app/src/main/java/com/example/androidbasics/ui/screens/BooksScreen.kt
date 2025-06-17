package com.example.androidbasics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.androidbasics.R
import com.example.androidbasics.model.Book

@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    viewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_medium)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            value = uiState.searchValue,
            onValueChange = viewModel::onChangeSearchValue,
            trailingIcon = {
                IconButton(
                    onClick = viewModel::onSearch
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            }
        )

        when (uiState.booksUiState) {
            is BooksUiState.Idle -> {}

            is BooksUiState.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }

            is BooksUiState.Error -> {
                Text(text = stringResource(R.string.request_error))
            }

            is BooksUiState.Success -> {
                BooksGrid(
                    books = (uiState.booksUiState as BooksUiState.Success).books,
                    onLoadMore = viewModel::loadMoreBooks
                )
            }

        }
    }

}

@Composable
fun BooksGrid(
    books: List<Book>,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.padding_medium))
) {

    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_low)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_low))
        ) {
            items(items = books, key = { it.id }) {
                BookItem(book = it)
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Button(onClick = onLoadMore) {
                    Text(text = stringResource(R.string.load_more))
                }
            }

        }

    }
}


@Composable
fun BookItem(book: Book, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(0.7f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.volumeInfo.imageLinks?.url?.replace("http", "https"))
                .crossfade(true)
                .build(),
            contentDescription = null, contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),

            )
    }
}