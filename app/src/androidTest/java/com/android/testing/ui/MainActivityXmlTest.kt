package com.android.testing.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.testing.R
import com.android.testing.rules.BaseInstrumentationRule
import org.junit.Rule
import org.junit.Test

class MainActivityXmlTest {

    @get:Rule(order = 1)
    val baseInstrumentationRule = BaseInstrumentationRule()

    @get:Rule(order = 2)
    val activityScenarioRule = ActivityScenarioRule(MainActivityXml::class.java)
    
    @Test
    fun whenTheFormIsFilledCorrectlyThenLogIn() {
        MainActivityXmlRobot()
            .enterName("Pedro")
            .enterPassword("Password123-")
            .enterEmail("test@gmail.com")
            .enterAge("18")
            .acceptTerms()
            .clickCreateAccount()
            .assertWelcomeMessageIsDisplayed("Pedro")

    }

}