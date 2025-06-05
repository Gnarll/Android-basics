package com.example.androidbasics.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.androidbasics.R
import com.example.androidbasics.model.Amphibian
import com.example.androidbasics.ui.AmphibianUiState
import com.example.androidbasics.ui.AmphibiansViewModel

@Composable
fun AmphibiansScreen(
    modifier: Modifier = Modifier,
    amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory),
) {
    val amphibiansUiState by amphibiansViewModel.uiState.collectAsState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Text(text = stringResource(R.string.amphibians_title))
        Spacer(Modifier.size(dimensionResource(R.dimen.padding_small)))

        when (amphibiansUiState) {
            is AmphibianUiState.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.amphibians_loading_error),
                    Toast.LENGTH_LONG
                ).show()
            }

            is AmphibianUiState.Success -> AmphibiansList(
                amphibians = (amphibiansUiState as AmphibianUiState.Success).amphibians,
                modifier = Modifier.fillMaxSize()
            )

            is AmphibianUiState.Loading -> {
                CircularProgressIndicator()
            }

        }

    }
}

@Composable
fun AmphibiansList(amphibians: List<Amphibian>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        amphibians.forEach {
            AmphibiansListItem(amphibian = it)
        }
    }
}

@Composable
fun AmphibiansListItem(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier
            .fillMaxSize()

    ) {
        Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(amphibian.imgSrc)
                    .crossfade(true).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = amphibian.description,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}