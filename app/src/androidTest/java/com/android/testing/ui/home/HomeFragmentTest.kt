package com.android.testing.ui.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.android.testing.R
import com.android.testing.data.database.UserEntity
import com.android.testing.di.DataProvider
import com.android.testing.rules.BaseInstrumentationRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeFragmentTest {

    @get:Rule
    val baseInstrumentationRule = BaseInstrumentationRule()

    @Test
    fun test() = runTest {
        val user = UserEntity(1, "Pedro", "test@gmail.com")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        DataProvider.provideDataBase(context).userDao().insert(user)

        launchFragmentInContainer<HomeFragment>(
            themeResId = R.style.Theme_TestingCourse
        )

        onView(withText("Bienvenido Pedro"))
            .check(matches(isDisplayed()))
    }

}