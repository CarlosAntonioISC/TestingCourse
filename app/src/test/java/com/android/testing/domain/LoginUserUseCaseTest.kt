package com.android.testing.domain

import com.android.testing.data.FakeUserRepository
import com.android.testing.data.StubLoginRepository
import com.android.testing.domain.usecase.LoginUserUseCase

class LoginUserUseCaseTest {

    private val suv = LoginUserUseCase(
        loginRepository = StubLoginRepository(),
        userRepository = FakeUserRepository()
    )


}