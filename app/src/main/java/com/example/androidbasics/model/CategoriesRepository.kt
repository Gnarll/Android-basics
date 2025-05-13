package com.example.androidbasics.model

import com.example.androidbasics.R

class CategoriesRepository {
    companion object {
        fun getCategories(): List<Category> {
            return listOf(
                Category(
                    name = R.string.category_name_1, // Restaurants
                    recommendedPlaces = listOf(
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_1,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_1
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_2,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_2
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_3,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_3
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_4,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_4
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_5,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_5
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_6,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_6
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_7,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_7
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_8,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_8
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_9,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_9
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_1_recommended_place_name_10,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_1_recommended_place_article_10
                        )
                    )
                ),
                Category(
                    name = R.string.category_name_2, // Cinemas
                    recommendedPlaces = listOf(
                        RecommendedPlace(
                            name = R.string.category_name_2_recommended_place_name_1,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_2_recommended_place_article_1
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_2_recommended_place_name_2,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_2_recommended_place_article_2
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_2_recommended_place_name_3,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_2_recommended_place_article_3
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_2_recommended_place_name_4,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_2_recommended_place_article_4
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_2_recommended_place_name_5,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_2_recommended_place_article_5
                        )
                    )
                ),
                Category(
                    name = R.string.category_name_3, // Parks
                    recommendedPlaces = listOf(
                        RecommendedPlace(
                            name = R.string.category_name_3_recommended_place_name_1,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_3_recommended_place_article_1
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_3_recommended_place_name_2,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_3_recommended_place_article_2
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_3_recommended_place_name_3,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_3_recommended_place_article_3
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_3_recommended_place_name_4,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_3_recommended_place_article_4
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_3_recommended_place_name_5,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_3_recommended_place_article_5
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_3_recommended_place_name_6,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_3_recommended_place_article_6
                        )
                    )
                ),
                Category(
                    name = R.string.category_name_4, // Shopping Malls
                    recommendedPlaces = listOf(
                        RecommendedPlace(
                            name = R.string.category_name_4_recommended_place_name_1,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_4_recommended_place_article_1
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_4_recommended_place_name_2,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_4_recommended_place_article_2
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_4_recommended_place_name_3,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_4_recommended_place_article_3
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_4_recommended_place_name_4,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_4_recommended_place_article_4
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_4_recommended_place_name_5,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_4_recommended_place_article_5
                        )
                    )
                ),
                Category(
                    name = R.string.category_name_5, // Museums
                    recommendedPlaces = listOf(
                        RecommendedPlace(
                            name = R.string.category_name_5_recommended_place_name_1,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_5_recommended_place_article_1
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_5_recommended_place_name_2,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_5_recommended_place_article_2
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_5_recommended_place_name_3,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_5_recommended_place_article_3
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_5_recommended_place_name_4,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_5_recommended_place_article_4
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_5_recommended_place_name_5,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_5_recommended_place_article_5
                        ),
                        RecommendedPlace(
                            name = R.string.category_name_5_recommended_place_name_6,
                            image = R.drawable.image_plug,
                            article = R.string.category_name_5_recommended_place_article_6
                        )
                    )
                )
            )
        }
    }
}