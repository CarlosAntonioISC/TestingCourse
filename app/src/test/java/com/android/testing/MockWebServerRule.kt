package com.android.testing

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MockWebServerRule: TestWatcher() {

    val mockWebServer = MockWebServer()

    override fun starting(description: Description?) {
        super.starting(description)
        mockWebServer.start()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mockWebServer.shutdown()
    }

    fun <Api>buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(api)
    }

}