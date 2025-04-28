package com.android.testing.data

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.android.testing.data.idlingresources.OkHttp3IdlingResource
import com.android.testing.data.rules.MockWebServerRule
import com.android.testing.di.DataProvider
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseEndToEndClass {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()
    private lateinit var idlingResource: IdlingResource

    @Before
    fun setUp() {
        idlingResource = OkHttp3IdlingResource.create("OkHttp", )
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

}