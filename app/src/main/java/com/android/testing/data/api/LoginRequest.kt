package com.android.testing.data.api

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)