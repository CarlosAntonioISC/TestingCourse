package com.android.testing.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.testing.R

class MainActivityXmlRobot {

    fun enterName(name: String): MainActivityXmlRobot {
        onView(withId(R.id.input_name))
            .perform(typeText(name), closeSoftKeyboard())
        return this
    }

    fun enterPassword(password: String): MainActivityXmlRobot {
        onView(withId(R.id.input_password))
            .perform(typeText(password), closeSoftKeyboard())
        return this
    }

    fun enterEmail(email: String): MainActivityXmlRobot {
        onView(withId(R.id.input_email))
            .perform(typeText(email), closeSoftKeyboard())
        return this
    }

    fun enterAge(age: String): MainActivityXmlRobot {
        onView(withId(R.id.input_age))
            .perform(typeText(age), closeSoftKeyboard())
        return this
    }

    fun acceptTerms(): MainActivityXmlRobot {
        onView(withId(R.id.checkbox_terms))
            .perform(click())
        return this
    }

    fun clickCreateAccount(): MainActivityXmlRobot {
        onView(withId(R.id.btn_create_account))
            .perform(click())
        return this
    }

    fun assertWelcomeMessageIsDisplayed(name: String): MainActivityXmlRobot {
        onView(withText("Bienvenido $name"))
            .check(matches(isDisplayed()))
        return this
    }

}