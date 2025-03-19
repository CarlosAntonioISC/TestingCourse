package com.android.testing.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.testing.domain.AgeValidator
import com.android.testing.domain.EmailValidator
import com.android.testing.domain.usecase.LoginUserUseCase
import com.android.testing.domain.NameValidator
import com.android.testing.domain.PasswordValidator
import com.android.testing.domain.models.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val nameValidator: NameValidator,
    private val ageValidator: AgeValidator,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onUserAction(action: LoginAction) {
        when (action) {
            LoginAction.Login -> login()
            is LoginAction.OnUserNameChange -> onUserNameChange(action.name)
            is LoginAction.OnUserEmailChange -> onUserEmailChange(action.email)
            is LoginAction.OnUserPasswordChange -> onUserPasswordChange(action.password)
            is LoginAction.OnUserAgeChange -> onUserAgeChange(action.age)
            is LoginAction.OnUserChangeTermsAndConditions -> onUserTermsAndConditionsChange(action.checked)
        }
    }

    private fun login() = viewModelScope.launch {
        uiState.value.let { state ->
            if (!state.canLogin) return@launch
            _uiState.update { it.copy(isLoading = true) }
            val loginResult = loginUserUseCase.invoke(
                userName = state.name!!,
                userPassword = state.password!!,
                userEmail = state.email!!
            )
            _uiState.update { it.copy(isLoading = false, loginSuccess = loginResult.isSuccess) }
        }
    }

    private fun onUserNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
        validateForm()
    }

    private fun onUserPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
        validateForm()
    }

    private fun onUserAgeChange(age: Int) {
        _uiState.update { it.copy(age = age) }
        validateForm()
    }

    private fun onUserEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
        validateForm()
    }

    private fun onUserTermsAndConditionsChange(checked: Boolean) {
        _uiState.update { it.copy(termsAndConditionsChecked = checked) }
        validateForm()
    }

    private fun validateForm() {
        val uiState = _uiState.value

        val allUserInfoIsValid = uiState.name?.let { nameValidator.isValid(it) } ?: false
                && uiState.password?.let { passwordValidator.isValid(it) } ?: false
                && uiState.email?.let { emailValidator.isValid(it) } ?: false
                && uiState.age?.let { ageValidator.isValid(it) } ?: false
                && uiState.termsAndConditionsChecked

        _uiState.update { it.copy(canLogin = allUserInfoIsValid) }
    }

}

