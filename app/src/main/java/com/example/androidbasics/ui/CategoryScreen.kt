package com.example.androidbasics.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidbasics.model.Category
import com.example.androidbasics.model.RecommendedPlace


@Composable
fun CategoryScreen(
    category: Category,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    modifier: Modifier = Modifier
) {
    Column { }
}

@Composable
fun RecommendationsList(
    recommendations: List<RecommendedPlace>,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {

    }
}

@Composable
fun RecommendationItem(
    recommendedPlace: RecommendedPlace,
    onRecommendedPlaceClick: (RecommendedPlace) -> Unit,
    modifier: Modifier = Modifier
) {

}