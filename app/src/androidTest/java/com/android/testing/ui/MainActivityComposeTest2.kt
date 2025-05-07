package com.android.testing.ui

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import com.android.testing.rules.BaseInstrumentationRule
import org.junit.Rule
import org.junit.Test

class MainActivityComposeTest2 {

    @get:Rule(order = 1)
    val baseInstrumentationRule = BaseInstrumentationRule()
    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivityCompose>()

    @Test
    fun whenTheFormIsFilledCorrectlyThenLogIn() {
        val fields = composeTestRule.onAllNodes(hasSetTextAction())
        fields[0].performTextInput("Pedro")
        fields[1].performTextInput("Password123-")
        fields[2].performTextInput("test@gmail.com")
        fields[3].performTextInput("18")

        composeTestRule
            .onNodeWithTag("He leído y acepto los términos y condiciones de uso")
            .performClick()

        composeTestRule
            .onNodeWithText("Crear cuenta")
            .performClick()

        composeTestRule.onNodeWithText("Bienvenido Pedro").assertExists()
    }


}