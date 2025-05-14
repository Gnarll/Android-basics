package com.example.androidbasics.ui

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androidbasics.R
import com.example.androidbasics.model.CategoriesRepository
import com.example.androidbasics.model.Category

@Composable
fun CityHomeScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    CategoriesList(
        categories = categories,
        onCategoryClick = onCategoryClick,
        contentPadding = 10.dp,
        modifier = modifier
    )
}

@Composable
fun CategoriesList(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
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
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(
                    onClick = { onCategoryClick(category) },
                )
                .padding(dimensionResource(R.dimen.padding_small)),
        ) {
            Image(
                painter = painterResource(category.image),
                contentDescription = stringResource(category.name),
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(dimensionResource(R.dimen.medium_image_size))
            )
            Text(text = stringResource(category.name), style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityHomeScreenPreview() {
    CityHomeScreen(
        categories = CategoriesRepository.getCategories(), onCategoryClick = {})
}



