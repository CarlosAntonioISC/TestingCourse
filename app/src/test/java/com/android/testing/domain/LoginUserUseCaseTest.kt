package com.android.testing.domain

import com.android.testing.domain.repository.LoginRepository
import com.android.testing.domain.repository.UserRepository
import com.android.testing.domain.usecase.LoginUserUseCase
import io.mockk.mockk

class LoginUserUseCaseTest {

    private val loginRepository: LoginRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val suv = LoginUserUseCase(
        loginRepository = loginRepository,
        userRepository = userRepository
    )


}