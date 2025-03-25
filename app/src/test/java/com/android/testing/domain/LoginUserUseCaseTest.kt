package com.android.testing.domain

import com.android.testing.data.FakeUserRepository
import com.android.testing.data.StubLoginRepository
import com.android.testing.domain.repository.LoginRepository
import com.android.testing.domain.repository.UserRepository
import com.android.testing.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginUserUseCaseTest {

    private val loginRepository: LoginRepository = StubLoginRepository()
    private val userRepository: UserRepository = FakeUserRepository()
    private val suv = LoginUserUseCase(
        loginRepository = loginRepository,
        userRepository = userRepository
    )

    @Test
    fun `When login return success then save user`() = runTest {
        val userName = "test name"
        suv.invoke(userName, "test", "test")
        assertEquals(userName, userRepository.get()!!.name)
    }

}