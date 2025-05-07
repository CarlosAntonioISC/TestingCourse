package com.android.testing.ui.home

import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

   @Test
   fun userNameIsDisplayed() {
       composeTestRule.setContent {
           HomeScreen(
               uiState = HomeUiState(userName = "Pedro"),
               onClick = { }
           )
       }

       composeTestRule.onNodeWithText("Bienvenido Pedro").assertExists()
       Thread.sleep(10000)
   }

    @Test
    fun whenFontSizeIsIncreasedThenButtonIsStillVisible() {
        var wasClicked = false

        composeTestRule.setContent {
            DeviceConfigurationOverride(
                DeviceConfigurationOverride.FontScale(2f)
            ) {
                HomeScreen(
                    uiState = HomeUiState(userName = "Pedro"),
                    onClick = { wasClicked = true }
                )
            }
        }

        composeTestRule.onNodeWithText("Logout").performScrollTo().performClick()

        assertTrue(wasClicked)

        Thread.sleep(10000)
    }

}