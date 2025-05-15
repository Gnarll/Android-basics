package com.example.androidbasics

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.androidbasics.ui.CategoryScreen
import com.example.androidbasics.ui.CityHomeScreen
import com.example.androidbasics.ui.CityViewModel
import com.example.androidbasics.ui.RecommendedPlaceScreen
import com.example.androidbasics.ui.theme.AndroidBasicsTheme
import com.example.androidbasics.ui.utils.CityContentType

enum class CityAppScreen(@StringRes val title: Int) {
    Home(title = R.string.home_screen_title),
    Category(title = R.string.home_screen_title),
    RecommendedPlace(title = R.string.recommended_place_screen_title),
}


@Composable
fun CityApp(
    navController: NavHostController = rememberNavController(),
    viewModel: CityViewModel = viewModel(),
    windowSizeWidth: WindowWidthSizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        CityAppScreen.valueOf(backStackEntry?.destination?.route ?: CityAppScreen.Home.name)
    val cityUiState by viewModel.cityUiState.collectAsState()
    val prevBackStackEntry = navController.previousBackStackEntry

    var categoryScreenTitle: String
    var recommendedPlaceScreenTitle: String
    with(cityUiState) {
        categoryScreenTitle = stringResource(currentCategory.name)
        recommendedPlaceScreenTitle =
            stringResource(currentRecommendedPlace.name)
    }


    val contentType: CityContentType

    when (windowSizeWidth) {
        WindowWidthSizeClass.COMPACT -> {
            contentType = CityContentType.LIST_ONLY
        }

        WindowWidthSizeClass.MEDIUM -> {
            contentType = CityContentType.LIST_ONLY
        }

        WindowWidthSizeClass.EXPANDED -> {
            contentType = CityContentType.LIST_AND_DETAIL
        }

        else -> {
            contentType = CityContentType.LIST_ONLY
        }
    }

    Scaffold(topBar = {
        CityAppTopBar(
            title = when (currentScreen) {
                CityAppScreen.Category -> categoryScreenTitle
                CityAppScreen.RecommendedPlace -> recommendedPlaceScreenTitle
                else -> stringResource(currentScreen.title)
            },
            canNavigateBack = prevBackStackEntry != null,
            navigateBack = { navController.popBackStack() })
    }) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = CityAppScreen.Home.name,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable(route = CityAppScreen.Home.name) {
                CityHomeScreen(
                    cityUiState = cityUiState,
                    setCategory = { category ->
                        viewModel.setCurrentCategory(category)
                    },
                    navigateToCategory = {
                        navController.navigate(route = CityAppScreen.Category.name)
                    },
                    onRecommendedPlaceClick = { recommendedPlace ->
                        viewModel.setCurrentRecommendedPlace(recommendedPlace)
                        navController.navigate(route = CityAppScreen.RecommendedPlace.name)
                    },
                    contentType = contentType
                )
            }
            composable(route = CityAppScreen.Category.name) {
                CategoryScreen(
                    category = cityUiState.currentCategory,
                    onRecommendedPlaceClick = { place ->
                        viewModel.setCurrentRecommendedPlace(place = place)
                        navController.navigate(route = CityAppScreen.RecommendedPlace.name)
                    })
            }
            composable(route = CityAppScreen.RecommendedPlace.name) {
                RecommendedPlaceScreen(
                    recommendedPlace = cityUiState.currentRecommendedPlace,
                    contentType = contentType
                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppTopBar(
    title: String,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(title = {
        Text(text = title)
    }, navigationIcon = {
        if (canNavigateBack) {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    }, modifier = modifier)
}


@Preview(widthDp = 500, showBackground = true, showSystemUi = true)
@Composable
private fun CityAppScreenCompactPreview(
) {
    AndroidBasicsTheme {
        CityApp(windowSizeWidth = WindowWidthSizeClass.COMPACT)
    }
}

@Preview(widthDp = 650)
@Composable
private fun CityAppScreenMediumPreview(
) {
    AndroidBasicsTheme {
        CityApp(windowSizeWidth = WindowWidthSizeClass.MEDIUM)
    }
}

@Preview(widthDp = 900)
@Composable
private fun CityAppScreenExpandedPreview(
) {
    AndroidBasicsTheme {
        CityApp(windowSizeWidth = WindowWidthSizeClass.EXPANDED)
    }
}
