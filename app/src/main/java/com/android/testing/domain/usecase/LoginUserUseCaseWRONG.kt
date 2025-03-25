package com.android.testing.domain.usecase

import android.content.Context
import com.android.testing.data.api.LoginApi
import com.android.testing.data.api.LoginRequest
import com.android.testing.data.database.AppDatabase
import com.android.testing.data.database.UserEntity
import com.android.testing.di.DataProvider
import com.android.testing.domain.models.DomainError
import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError
import com.android.testing.domain.models.User
import com.android.testing.domain.models.UserId

class LoginUserUseCaseWRONG(
    private val context: Context
) {

    suspend operator fun invoke(
        userName: String,
        userPassword: String,
        userEmail: String
    ): Result<Boolean, DomainError> {

        val loginResult = makeRequest(
            userName = userName,
            userPassword = userPassword,
            userEmail = userEmail
        )

        return when (loginResult) {
            is Result.Error -> loginResult
            is Result.Success -> {
                val user = User(id = loginResult.data, name = userName, email = userEmail)
                saveUser(user)
                Result.Success(true)
            }
        }
    }

    private suspend fun makeRequest(
        userName: String,
        userPassword: String,
        userEmail: String
    ): Result<UserId, ServerError> {
        return try {
            val request = LoginRequest(name = userName, password = userPassword, email = userEmail)

            val response = DataProvider.provideApi(LoginApi::class.java).login(request)

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

    private suspend fun saveUser(user: User) {
        val userEntity = UserEntity(
            id = user.id.value,
            name = user.name,
            email = user.email
        )
        val dao = AppDatabase.getInstance(context).userDao()
        dao.insert(userEntity)
    }

}