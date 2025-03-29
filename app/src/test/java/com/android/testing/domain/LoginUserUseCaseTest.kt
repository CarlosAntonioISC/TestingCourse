package com.android.testing.domain

import com.android.testing.data.PrintLogSender
import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError
import com.android.testing.domain.models.User
import com.android.testing.domain.models.UserId
import com.android.testing.domain.repository.LoginRepository
import com.android.testing.domain.repository.UserRepository
import com.android.testing.domain.usecase.LoginUserUseCase
import com.android.testing.examples.LogSender
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginUserUseCaseTest {

    private val loginRepository: LoginRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val logSender: LogSender = spyk { PrintLogSender() }
    private val sut = LoginUserUseCase(
        loginRepository = loginRepository,
        userRepository = userRepository,
        logSender
    )

    @Before
    fun setUp() {
        clearMocks(loginRepository, userRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When login return success then save user`() = runTest {
        val userId = UserId(1)
        val userName = "carlos"
        val userPassword = "password123"
        val userEmail = "carlos@gmail.com"

        coEvery {
            loginRepository.login(
                userName = userName,
                userPassword = userPassword,
                userEmail = userEmail
            )
        } returns Result.Success(userId)

        coJustRun { userRepository.save(User(userId, userName, userEmail)) }

        sut.invoke(userName, userPassword, userEmail)

        coVerify(exactly = 1) { userRepository.save(User(userId, userName, userEmail) ) }
    }

    @Test
    fun `When login return error then don't save user`() = runTest {
        coEvery {
            loginRepository.login(userName = any(), userPassword = any(), userEmail = any())
        } returns Result.Error(ServerError)

        sut.invoke("userName", "userPassword", "userEmail")

        coVerify(exactly = 0) { userRepository.save(any()) }
    }

    @Test
    fun `When login return error then call log sender`() = runTest {
        coEvery {
            loginRepository.login(userName = any(), userPassword = any(), userEmail = any())
        } returns Result.Error(ServerError)

        sut.invoke("userName", "userPassword", "userEmail")

        verify(exactly = 1) { logSender.send(any()) }
    }

    @Test
    fun `When login return success then save the user with id from login response`() = runTest {
        val userId = UserId(99)
        val userSlot = slot<User>()
        coEvery { loginRepository.login(any(), any(), any()) } returns Result.Success(userId)
        coJustRun { userRepository.save(capture(userSlot)) }

        sut.invoke("userName", "userPassword", "userEmail")

        assertEquals(userId, userSlot.captured.id)
    }

}