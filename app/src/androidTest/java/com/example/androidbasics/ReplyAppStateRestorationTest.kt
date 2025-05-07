package com.example.androidbasics

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.performClick
import com.example.androidbasics.data.local.LocalEmailsDataProvider
import com.example.androidbasics.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_configurationChangedWithSelectedEmail_selectedEmailRetained() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        val emailBody =
            LocalEmailsDataProvider.allEmails[2].body
        composeTestRule.onNodeWithTextForStringId(emailBody).assertIsDisplayed()
        composeTestRule.onNodeWithTextForStringId(emailBody).performClick()

        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertExists()

        composeTestRule.onNodeWithTextForStringId(
            emailBody
        ).assertExists()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithTextForStringId(
            emailBody
        ).assertExists()
    }

    @Test
    fun expandedScreen_configurationChangedWithSelectedEmail_selectedEmailRetained() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)

        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }

        val emailBody = LocalEmailsDataProvider.allEmails[2].body
        composeTestRule.onNodeWithTextForStringId(emailBody)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTextForStringId(emailBody)
            .performClick()

        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren().assertAny(
            hasAnyDescendant(hasText(composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)))
        )

        stateRestorationTester.emulateSavedInstanceStateRestore()
        
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren().assertAny(
            hasAnyDescendant(hasText(composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)))
        )

    }
}