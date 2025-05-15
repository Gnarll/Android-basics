package com.example.androidbasics.ui

import com.example.androidbasics.model.CategoriesRepository
import com.example.androidbasics.model.Category
import com.example.androidbasics.model.RecommendedPlace


data class CityUiState(
    val categories: List<Category> = CategoriesRepository.categories,
    val currentCategory: Category = CategoriesRepository.Defaults.category,
    val currentRecommendedPlace: RecommendedPlace = CategoriesRepository.Defaults.recommendedPlace
)
