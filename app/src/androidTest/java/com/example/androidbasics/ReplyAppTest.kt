package com.example.androidbasics

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.androidbasics.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_verifyUsingBottomNavigation() {
        val windowSize = WindowWidthSizeClass.Compact
        composeTestRule.setContent {
            ReplyApp(windowSize = windowSize)
        }

        composeTestRule.onNodeWithTagForStringId(R.string.navigation_bottom).assertExists()
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        val windowSize = WindowWidthSizeClass.Medium
        composeTestRule.setContent {
            ReplyApp(windowSize = windowSize)
        }

        composeTestRule.onNodeWithTagForStringId(R.string.navigation_rail).assertExists()
    }

    @Test
    fun expandedDevice_verifyUsingNavigationDrawer() {
        val windowSize = WindowWidthSizeClass.Expanded
        composeTestRule.setContent {
            ReplyApp(windowSize = windowSize)
        }

        composeTestRule.onNodeWithTagForStringId(R.string.navigation_drawer).assertExists()
    }
}