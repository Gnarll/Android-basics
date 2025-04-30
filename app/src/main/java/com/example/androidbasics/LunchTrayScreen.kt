/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androidbasics

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidbasics.datasource.DataSource
import com.example.androidbasics.ui.AccompanimentMenuScreen
import com.example.androidbasics.ui.CheckoutScreen
import com.example.androidbasics.ui.EntreeMenuScreen
import com.example.androidbasics.ui.OrderViewModel
import com.example.androidbasics.ui.SideDishMenuScreen
import com.example.androidbasics.ui.StartOrderScreen

enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.start_order),
    EntreeMenu(title = R.string.entree_menu),
    SideDishMenu(title = R.string.side_dish),
    AccompanimentMenu(title = R.string.order_summary),
    Checkout(title = R.string.checkout)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayTopAppBar(
    currentScreen: LunchTrayScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(title = { Text(text = stringResource(currentScreen.title)) }, navigationIcon = {
        if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    navController: NavHostController = rememberNavController(),
    viewModel: OrderViewModel = viewModel()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        LunchTrayScreen.valueOf(backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name)

    Scaffold(
        topBar = {
            LunchTrayTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(onStartOrderButtonClicked = {
                    navController.navigate(route = LunchTrayScreen.EntreeMenu.name)
                }, modifier = Modifier.fillMaxSize())
            }
            composable(route = LunchTrayScreen.EntreeMenu.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        navigateAtStartScreenAndResetOrder(
                            navController,
                            viewModel
                        )
                    },
                    onNextButtonClicked = { navController.navigate(route = LunchTrayScreen.SideDishMenu.name) },
                    onSelectionChanged = { entree ->
                        viewModel.updateEntree(entree = entree)
                    }, modifier = Modifier.verticalScroll(state = rememberScrollState())
                )

            }
            composable(route = LunchTrayScreen.SideDishMenu.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        navigateAtStartScreenAndResetOrder(
                            navController,
                            viewModel
                        )
                    },
                    onNextButtonClicked = { navController.navigate(route = LunchTrayScreen.AccompanimentMenu.name) },
                    onSelectionChanged = { sideDish ->
                        viewModel.updateSideDish(sideDish = sideDish)
                    }, modifier = Modifier.verticalScroll(state = rememberScrollState())
                )
            }
            composable(route = LunchTrayScreen.AccompanimentMenu.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        navigateAtStartScreenAndResetOrder(
                            navController,
                            viewModel
                        )
                    },
                    onNextButtonClicked = { navController.navigate(route = LunchTrayScreen.Checkout.name) },
                    onSelectionChanged = { accompaniment ->
                        viewModel.updateAccompaniment(accompaniment)
                    }, modifier = Modifier.verticalScroll(state = rememberScrollState())
                )
            }
            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        navigateAtStartScreenAndResetOrder(
                            navController,
                            viewModel
                        )
                    },
                    onNextButtonClicked = {
                        navigateAtStartScreenAndResetOrder(
                            navController,
                            viewModel
                        )
                    },
                    modifier = Modifier.verticalScroll(state = rememberScrollState())
                )
            }

        }
    }
}

fun navigateAtStartScreenAndResetOrder(
    navController: NavHostController,
    viewModel: OrderViewModel
) {
    viewModel.resetOrder()
    navController.popBackStack(route = LunchTrayScreen.Start.name, inclusive = false)
}
