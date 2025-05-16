package com.example.androidbasics.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androidbasics.R
import com.example.androidbasics.model.RecommendedPlace
import com.example.androidbasics.ui.utils.CityContentType


@Composable
fun CategoryScreen(
    cityUiState: CityUiState,
    contentType: CityContentType,
    setRecommendedPlace: (RecommendedPlace) -> Unit,
    navigateToCategory: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (contentType == CityContentType.LIST_AND_DETAIL) {
        RecommendationsListAndDetail(
            recommendations = cityUiState.currentCategory.recommendedPlaces,
            selectedRecommendedPlace = cityUiState.currentRecommendedPlace,
            onRecommendedPlaceClick = setRecommendedPlace,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    } else {
        RecommendationsList(
            recommendations = cityUiState.currentCategory.recommendedPlaces,
            onRecommendedPlaceClick = { place ->
                setRecommendedPlace(place)
                navigateToCategory()
            },
            contentPadding = dimensionResource(R.dimen.padding_small),
            modifier = modifier
        )
    }
}

@Composable
fun RecommendationsList(
    recommendations: List<RecommendedPlace>,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    contentPadding: Dp = 0.dp,
    selectedRecommendedPlace: RecommendedPlace? = null,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier,
        contentPadding = PaddingValues(contentPadding),
    ) {
        items(items = recommendations, key = { it.name }) { item ->
            RecommendationItem(
                recommendedPlace = item,
                onRecommendedPlaceClick = onRecommendedPlaceClick,
                isActiveItem = selectedRecommendedPlace?.let { it == item } == true
            )

        }
    }
}

@Composable
fun RecommendationItem(
    recommendedPlace: RecommendedPlace,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    modifier: Modifier = Modifier,
    isActiveItem: Boolean = false,
) {
    val itemContainerColorAnimated
            by animateColorAsState(
                targetValue =
                    if (isActiveItem) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surfaceContainerHighest
            )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onRecommendedPlaceClick(recommendedPlace) },
            ),
        colors = CardDefaults.cardColors(containerColor = itemContainerColorAnimated)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier

                .padding(dimensionResource(R.dimen.padding_small)),
        ) {
            Image(
                painter = painterResource(recommendedPlace.image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(dimensionResource(R.dimen.medium_image_size))
            )
            Text(
                text = stringResource(recommendedPlace.name),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun RecommendationsListAndDetail(
    recommendations: List<RecommendedPlace>,
    selectedRecommendedPlace: RecommendedPlace,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        RecommendationsList(
            recommendations = recommendations,
            selectedRecommendedPlace = selectedRecommendedPlace,
            onRecommendedPlaceClick = onRecommendedPlaceClick, modifier = Modifier.weight(1f)
        )
        RecommendedPlaceItemExtended(
            recommendedPlace = selectedRecommendedPlace,
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        )

    }
}
