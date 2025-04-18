package com.android.testing.data.repository

import com.android.testing.MockWebServerRule
import com.android.testing.data.api.LoginApi
import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError
import com.android.testing.domain.models.UserId
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RetrofitLoginRepositoryTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()
    private lateinit var sut: RetrofitLoginRepository

    @Before
    fun setUp() {
        sut = RetrofitLoginRepository(mockWebServerRule.buildApi(LoginApi::class.java))
    }

    @Test
    fun `When api response success then return id user from api`() = runTest {
        val mockResponse = MockResponse().setResponseCode(200).setBody(
            """
                {
                  "id": 95,
                  "username": "string",
                  "email": "string",
                  "password": "string"
                }
        """.trimIndent()
        )
        mockWebServerRule.mockWebServer.enqueue(mockResponse)

        val result = sut
            .login("carlos", "password123", "test@gmail.com")

        assertEquals(Result.Success(UserId(95)), result)
    }

    @Test
    fun `When api response success then return id user from api 2`() = runTest {
        mockWebServerRule.mockWebServer.dispatcher = CustomDispatcher()

        val result = sut
            .login("carlos", "password123", "test@gmail.com")

        assertEquals(Result.Success(UserId(95)), result)
    }

    @Test
    fun `When api response error then return SERVER_ERROR`() = runTest {
        val mockResponse = MockResponse().setResponseCode(500)
        mockWebServerRule.mockWebServer.enqueue(mockResponse)

        val result = sut
            .login("carlos", "password123", "test@gmail.com")

        assertEquals(Result.Error(ServerError), result)
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