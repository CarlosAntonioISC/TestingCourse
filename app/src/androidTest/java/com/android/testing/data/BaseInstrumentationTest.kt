package com.android.testing.data

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.android.testing.data.idlingresources.OkHttp3IdlingResource
import com.android.testing.data.rules.MockWebServerRule
import com.android.testing.di.DataProvider
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseInstrumentationTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()
    private lateinit var idlingResource: IdlingResource

    @Before
    fun setUp() {
        DataProvider.baseTestingUrl = mockWebServerRule.mockWebServer.url("/").toString()
        idlingResource = OkHttp3IdlingResource.create("OkHttp", DataProvider.okHttpClient)
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

}