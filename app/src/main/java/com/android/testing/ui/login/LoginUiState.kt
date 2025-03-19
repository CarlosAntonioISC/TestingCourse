package com.android.testing.ui.login

data class LoginUiState(
    val name: String? = null,
    val password: String? = null,
    val age: Int? = null,
    val email: String? = null,
    val termsAndConditionsChecked: Boolean = false,
    val isLoading: Boolean = false,
    val canLogin: Boolean = false,
    val loginSuccess: Boolean = false
)