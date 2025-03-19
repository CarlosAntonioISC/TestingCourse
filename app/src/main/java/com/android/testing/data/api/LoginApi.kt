package com.android.testing.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("users")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

}