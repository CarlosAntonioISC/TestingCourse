package com.android.testing.domain

import com.android.testing.data.FakeLoginRepository
import com.android.testing.data.FakeUserRepository
import com.android.testing.data.PrintLogSender
import com.android.testing.domain.models.ServerError
import com.android.testing.domain.models.User
import com.android.testing.domain.models.UserId
import com.android.testing.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LoginUserUseCaseStateTest {

    private val loginRepository = FakeLoginRepository()
    private val userRepository = FakeUserRepository()
    private val logSender = PrintLogSender()
    private val sut = LoginUserUseCase(
        loginRepository = loginRepository,
        userRepository = userRepository,
        logSender
    )

    @Test
    fun `When login return success then save user`() = runTest {
        val userId = UserId(1)
        val userName = "carlos"
        val userPassword = "password123"
        val userEmail = "carlos@gmail.com"
        loginRepository.idUserToReturn = userId

        sut.invoke(userName, userPassword, userEmail)

        val userSaved = userRepository.get()
        val userExpected = User(id = userId, name = userName, email = userEmail)
        assertEquals(userExpected, userSaved)
    }

    @Test
    fun `When login return error then don't save user`() = runTest {
        loginRepository.errorToReturn = ServerError

        sut.invoke("carlos", "password123", "carlos@gmail.com")

        val userSaved = userRepository.get()
        assertNull(userSaved)
    }

}