package com.example.androidbasics

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule


fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTagForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction {
    return onNodeWithTag(activity.getString(id))
}

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithContentDescriptionForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction {
    return onNodeWithContentDescription(activity.getString(id))
}

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTextForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction {
    return onNodeWithText(activity.getString(id))
}

