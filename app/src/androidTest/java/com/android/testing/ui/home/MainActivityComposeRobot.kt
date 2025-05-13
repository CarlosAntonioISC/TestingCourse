package com.android.testing.ui.home

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class MainActivityComposeRobot(
    val composeTestRule: ComposeContentTestRule
) {


    fun enterName(name: String): MainActivityComposeRobot {
        val fields = composeTestRule.onAllNodes(hasSetTextAction())
        fields[0].performTextInput("Pedro")
        return this
    }

    fun enterPassword(password: String): MainActivityComposeRobot {
        val fields = composeTestRule.onAllNodes(hasSetTextAction())
        fields[1].performTextInput("Password123-")
        return this
    }

    fun enterEmail(email: String): MainActivityComposeRobot {
        val fields = composeTestRule.onAllNodes(hasSetTextAction())
        fields[2].performTextInput("test@gmail.com")
        return this
    }

    fun enterAge(age: String): MainActivityComposeRobot {
        val fields = composeTestRule.onAllNodes(hasSetTextAction())
        fields[3].performTextInput("18")
        return this
    }

    fun acceptTerms(): MainActivityComposeRobot {
        composeTestRule
            .onNodeWithTag("He leído y acepto los términos y condiciones de uso")
            .performClick()
        return this
    }

    fun clickCreateAccount(): MainActivityComposeRobot {
        composeTestRule
            .onNodeWithText("Crear cuenta")
            .performClick()
        return this
    }

    fun assertWelcomeMessageIsDisplayed(name: String): MainActivityComposeRobot {
        composeTestRule.onNodeWithText("Bienvenido $name").assertExists()
        return this
    }

}