package com.example.androidbasics.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidbasics.R
import com.example.androidbasics.model.CategoriesRepository
import com.example.androidbasics.model.RecommendedPlace
import com.example.androidbasics.ui.utils.CityContentType

@Composable
fun RecommendedPlaceScreen(
    recommendedPlace: RecommendedPlace,
    contentType: CityContentType,
    modifier: Modifier = Modifier
) {
    if (contentType == CityContentType.LIST_AND_DETAIL) {
        RecommendedPlaceItemExtendedRow(
            recommendedPlace = recommendedPlace,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
        )

    } else {
        RecommendedPlaceItemExtended(
            recommendedPlace = recommendedPlace,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    }

}

@Composable
fun RecommendedPlaceItemExtended(
    recommendedPlace: RecommendedPlace,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(recommendedPlace.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = stringResource(recommendedPlace.article),
            style = MaterialTheme.typography.displaySmall,
        )
    }
}

@Composable
fun RecommendedPlaceItemExtendedRow(
    recommendedPlace: RecommendedPlace,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(recommendedPlace.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(recommendedPlace.article),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.weight(1f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RecommendedPlaceItemExtendedPreview() {
    RecommendedPlaceItemExtended(
        CategoriesRepository.Defaults.recommendedPlace
    )
}

@Preview(showBackground = true)
@Composable
fun RecommendedPlaceItemExtendedRowPreview() {
    RecommendedPlaceItemExtendedRow(
        CategoriesRepository.Defaults.recommendedPlace
    )
}