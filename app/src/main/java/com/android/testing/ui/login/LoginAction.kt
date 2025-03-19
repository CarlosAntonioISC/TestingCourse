package com.android.testing.ui.login

sealed interface LoginAction {
    data class OnUserNameChange(val name: String): LoginAction
    data class OnUserPasswordChange(val password: String): LoginAction
    data class OnUserEmailChange(val email: String): LoginAction
    data class OnUserAgeChange(val age: Int): LoginAction
    data class OnUserChangeTermsAndConditions(val checked: Boolean): LoginAction
    data object Login: LoginAction
}