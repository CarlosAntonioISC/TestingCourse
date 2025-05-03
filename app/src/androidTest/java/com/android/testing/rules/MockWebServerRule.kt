package com.android.testing.rules

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule: TestWatcher() {

    val mockWebServer = MockWebServer()

    override fun starting(description: Description?) {
        super.starting(description)
        mockWebServer.start()
        mockWebServer.dispatcher = CustomDispatcher()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mockWebServer.shutdown()
    }

}

class CustomDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/users" -> {
                MockResponse().setResponseCode(200).setBody(
                    """
                        {
                          "id": 95,
                          "username": "string",
                          "email": "string",
                          "password": "string"
                        }
                    """.trimIndent()
                )
            }

            else -> MockResponse().setResponseCode(500)
        }
    }

}