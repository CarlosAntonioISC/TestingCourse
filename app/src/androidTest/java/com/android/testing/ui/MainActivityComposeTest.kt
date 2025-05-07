package com.android.testing.ui

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import com.android.testing.BaseInstrumentationTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MainActivityComposeTest: BaseInstrumentationTest() {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenTheFormIsFilledCorrectlyThenLogIn() = runTest {
        ActivityScenario.launch(MainActivityCompose::class.java)

        //composeTestRule
        //    .onNode(hasText("Nombre") and hasSetTextAction())
        //    .performTextInput("Pedro")

        //composeTestRule
        //    .onNodeWithContentDescription("Nombre")
        //    .performTextInput("Pedro")

        //composeTestRule
        //    .onNodeWithTag("Nombre")
        //    .performTextInput("Pedro")

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