package com.android.testing.data

import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError
import com.android.testing.domain.models.UserId
import com.android.testing.domain.repository.LoginRepository

class FakeLoginRepository : LoginRepository {

    var errorToReturn: ServerError? = null
    var idUserToReturn: UserId = UserId(1)

    override suspend fun login(
        userName: String,
        userPassword: String,
        userEmail: String
    ): Result<UserId, ServerError> {
        return if (errorToReturn == null) {
            Result.Success(idUserToReturn)
        } else {
            Result.Error(errorToReturn!!)
        }
    }


}