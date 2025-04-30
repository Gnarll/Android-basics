package com.example.androidbasics

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.androidbasics.data.DataSource
import com.example.androidbasics.data.OrderUiState
import com.example.androidbasics.ui.OrderSummaryScreen
import com.example.androidbasics.ui.SelectOptionScreen
import com.example.androidbasics.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val mockUiState: OrderUiState = OrderUiState(
        quantity = 10,
        flavor = "Apple",
        date = "Wed Jan 1",
        price = "$500",
        pickupOptions = listOf()
    )

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(
                subtotal = subtotal,
                options = flavors,
                onCancelButtonClicked = {},
                onNextButtonClicked = {})
        }

        flavors.forEach {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(DataSource.quantityOptions, onNextButtonClicked = {})
        }

        DataSource.quantityOptions.forEach { options ->
            composeTestRule.onNodeWithStringId(options.first).assertIsDisplayed()
        }
    }

    @Test
    fun orderSummaryScreen_verifyContent() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = mockUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> })
        }

        composeTestRule.onNodeWithText("${mockUiState.quantity} cupcakes").assertIsDisplayed()
        composeTestRule.onNodeWithText(mockUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockUiState.date).assertIsDisplayed()

        val subtotalPrice =
            composeTestRule.activity.getString(R.string.subtotal_price, mockUiState.price)
        composeTestRule.onNodeWithText(subtotalPrice).assertIsDisplayed()

    }

    @Test
    fun chooseFlavorScreen_optionIsChosen_NextButtonIsEnabled() {
        val mockOptions = listOf("option1", "option2")

        composeTestRule.setContent {
            SelectOptionScreen(
                subtotal = "",
                options = mockOptions,
                onNextButtonClicked = {},
                onSelectionChanged = { _ -> },
                onCancelButtonClicked = {})
        }

        val firstOption = mockOptions.get(0)

        composeTestRule.onNodeWithText(firstOption).performClick()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}