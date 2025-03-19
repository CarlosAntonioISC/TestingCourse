package com.android.testing.data.api

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val userId: Int
)