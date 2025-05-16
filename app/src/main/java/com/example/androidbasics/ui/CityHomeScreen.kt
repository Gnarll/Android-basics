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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androidbasics.R
import com.example.androidbasics.model.CategoriesRepository
import com.example.androidbasics.model.Category
import com.example.androidbasics.model.RecommendedPlace
import com.example.androidbasics.ui.utils.CityContentType

@Composable
fun CityHomeScreen(
    cityUiState: CityUiState,
    setCategory: (Category) -> Unit,
    navigateToCategory: () -> Unit,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    contentType: CityContentType,
    modifier: Modifier = Modifier
) {
    if (contentType == CityContentType.LIST_AND_DETAIL) {
        CategoriesListAndDetail(
            categories = cityUiState.categories,
            selectedCategory = cityUiState.currentCategory,
            onCategoryClick = setCategory,
            onRecommendedPlaceClick = onRecommendedPlaceClick,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    } else {
        CategoriesList(
            categories = cityUiState.categories,
            onCategoryClick = {
                setCategory(it)
                navigateToCategory()
            },
            contentPadding = dimensionResource(R.dimen.padding_small),
            modifier = modifier
        )
    }
}

@Composable
fun CategoriesList(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    selectedCategory: Category? = null,
    modifier: Modifier = Modifier,
    contentPadding: Dp = 0.dp,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier,
        contentPadding = PaddingValues(contentPadding),
    ) {
        items(items = categories, key = {
            it.name
        }) {
            CategoryItem(
                category = it,
                onCategoryClick = onCategoryClick,
                isActiveItem = selectedCategory == it
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onCategoryClick: (Category) -> Unit,
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
                onClick = { onCategoryClick(category) },
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
                painter = painterResource(category.image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(dimensionResource(R.dimen.medium_image_size))
            )
            Text(text = stringResource(category.name), style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun CategoriesListAndDetail(
    categories: List<Category>,
    selectedCategory: Category,
    onCategoryClick: (Category) -> Unit,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        CategoriesList(
            categories = categories,
            onCategoryClick = onCategoryClick,
            selectedCategory = selectedCategory,
            modifier = Modifier.weight(1f)
        )
        RecommendationsList(
            recommendations = selectedCategory.recommendedPlaces,
            onRecommendedPlaceClick = onRecommendedPlaceClick,
            modifier = Modifier
                .weight(1f)
                .padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview(showBackground = true, widthDp = 850)
@Composable
fun CategoriesListAndDetailPreview() {
    CategoriesListAndDetail(
        categories = CategoriesRepository.categories,
        selectedCategory = CategoriesRepository.Defaults.category,
        onCategoryClick = {},
        onRecommendedPlaceClick = {})

}

@Preview(showBackground = true)
@Composable
fun CategoriesListPreview() {
    CategoriesList(
        categories = CategoriesRepository.categories,
        selectedCategory = CategoriesRepository.Defaults.category,
        onCategoryClick = {})
}




