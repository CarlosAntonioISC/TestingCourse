package com.android.testing.data.repository

import com.android.testing.data.api.LoginApi
import com.android.testing.data.api.LoginRequest
import com.android.testing.domain.UserId
import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError
import com.android.testing.domain.repository.LoginRepository

class RetrofitLoginRepository(
    private val loginApi: LoginApi
) : LoginRepository {

    override suspend fun login(
        userName: String,
        userPassword: String,
        userEmail: String
    ): Result<UserId, ServerError> {
        return try {
            val request = LoginRequest(name = userName, password = userPassword, email = userEmail)

            val response = loginApi.login(request)

            if (response.isSuccessful && response.body() != null) {
                val userId = response.body()!!.userId
                Result.Success(UserId(userId))
            } else {
                Result.Error(ServerError)
            }
        } catch (e: Exception) {
            Result.Error(ServerError)
        }
    }

}