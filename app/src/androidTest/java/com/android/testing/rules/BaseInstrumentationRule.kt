package com.android.testing.rules

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.platform.app.InstrumentationRegistry
import com.android.testing.di.DataProvider
import com.android.testing.idlingresources.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class BaseInstrumentationRule: TestWatcher() {

    private lateinit var idlingResource: IdlingResource
    private val mockWebServer = MockWebServer()

    override fun starting(description: Description?) {
        mockWebServer.start()
        mockWebServer.dispatcher = CustomDispatcher()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        DataProvider.isTestingEnvironment = true
        DataProvider.provideDataBase(context).clearAllTables()
        DataProvider.baseTestingUrl = mockWebServer.url("/").toString()
        idlingResource = OkHttp3IdlingResource.create("OkHttp", DataProvider.okHttpClient)
        IdlingRegistry.getInstance().register(idlingResource)
    }

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        mockWebServer.shutdown()
    }

}