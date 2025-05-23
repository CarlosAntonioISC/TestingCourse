package com.android.testing

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.platform.app.InstrumentationRegistry
import com.android.testing.idlingresources.OkHttp3IdlingResource
import com.android.testing.rules.MockWebServerRule
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
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        DataProvider.isTestingEnvironment = true
        DataProvider.provideDataBase(context).clearAllTables()
        DataProvider.baseTestingUrl = mockWebServerRule.mockWebServer.url("/").toString()
        idlingResource = OkHttp3IdlingResource.create("OkHttp", DataProvider.okHttpClient)
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

}