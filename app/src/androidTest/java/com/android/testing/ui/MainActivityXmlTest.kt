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

        onView(withId(R.id.input_name))
            .perform(typeText("Pedro"), closeSoftKeyboard())

        onView(withId(R.id.input_password))
            .perform(typeText("Password123-"), closeSoftKeyboard())

        onView(withId(R.id.input_email))
            .perform(typeText("test@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.input_age))
            .perform(typeText("18"), closeSoftKeyboard())


        onView(withId(R.id.checkbox_terms))
            .perform(click())

        onView(withId(R.id.btn_create_account))
            .perform(click())

        onView(withText("Bienvenido Pedro"))
            .check(matches(isDisplayed()))
    }

}