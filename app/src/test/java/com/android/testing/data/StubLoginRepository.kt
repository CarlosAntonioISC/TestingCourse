package com.android.testing.data

import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError
import com.android.testing.domain.models.UserId
import com.android.testing.domain.repository.LoginRepository

class StubLoginRepository: LoginRepository {

    override suspend fun login(
        userName: String,
        userPassword: String,
        userEmail: String
    ): Result<UserId, ServerError> {
        return Result.Success(UserId(1))
    }
}