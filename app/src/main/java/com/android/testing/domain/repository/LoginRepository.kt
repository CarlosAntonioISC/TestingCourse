package com.android.testing.domain.repository

import com.android.testing.domain.models.UserId
import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError

interface LoginRepository {

    suspend fun login(
        userName: String, userPassword: String, userEmail: String
    ): Result<UserId, ServerError>

}