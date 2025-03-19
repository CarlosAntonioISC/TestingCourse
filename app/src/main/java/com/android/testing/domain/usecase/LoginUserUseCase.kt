package com.android.testing.domain.usecase

import com.android.testing.domain.User
import com.android.testing.domain.models.DomainError
import com.android.testing.domain.repository.LoginRepository
import com.android.testing.domain.repository.UserRepository
import com.android.testing.domain.models.Result

class LoginUserUseCase(
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        userName: String,
        userPassword: String,
        userEmail: String
    ): Result<Boolean, DomainError> {

        val loginResult = loginRepository.login(
            userName = userName,
            userPassword = userPassword,
            userEmail = userEmail
        )

        return when (loginResult) {
            is Result.Error -> loginResult
            is Result.Success -> {
                val user = User(id = loginResult.data, name = userName, email = userEmail)
                userRepository.save(user)
                Result.Success(true)
            }
        }
    }

}