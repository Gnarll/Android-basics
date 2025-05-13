package com.example.androidbasics.ui

import com.example.androidbasics.model.CategoriesRepository
import com.example.androidbasics.model.Category
import com.example.androidbasics.model.RecommendedPlace


data class CityUiState(
    val categories: List<Category> = CategoriesRepository.getCategories(),
    val currentCategory: Category? = null,
    val currentRecommendedPlace: RecommendedPlace? = null
)
