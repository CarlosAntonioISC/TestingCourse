package com.android.testing.ui.login

import app.cash.turbine.test
import com.android.testing.CoroutineTestRule
import com.android.testing.data.StubEmailValidator
import com.android.testing.domain.AgeValidator
import com.android.testing.domain.NameValidator
import com.android.testing.domain.PasswordValidator
import com.android.testing.domain.models.Result
import com.android.testing.domain.usecase.LoginUserUseCase
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private val loginUserUseCase: LoginUserUseCase = mockk()
    private val emailValidator = StubEmailValidator()
    private val viewModel = LoginViewModel(
        nameValidator = NameValidator(),
        ageValidator = AgeValidator(),
        emailValidator = emailValidator,
        passwordValidator = PasswordValidator(),
        loginUserUseCase = loginUserUseCase
    )

    @Before
    fun setUp() {
        clearMocks(loginUserUseCase)
    }

    @Test
    fun `OnUserNameChange action should update ui state`() {
        val name = "Pablo"

        viewModel.onUserAction(LoginAction.OnUserNameChange(name))

        assertEquals(name, viewModel.uiState.value.name)
    }

    @Test
    fun `OnUserPasswordChange action should update ui state`() {
        val password = "Pablo"

        viewModel.onUserAction(LoginAction.OnUserPasswordChange(password))

        assertEquals(password, viewModel.uiState.value.password)
    }

    @Test
    fun `OnUserEmailChange action should update ui state`() {
        val email = "test@gmail.com"

        viewModel.onUserAction(LoginAction.OnUserEmailChange(email))

        assertEquals(email, viewModel.uiState.value.email)
    }

    @Test
    fun `OnUserAgeChange action should update ui state`() {
        val age = 20

        viewModel.onUserAction(LoginAction.OnUserAgeChange(age))

        assertEquals(age, viewModel.uiState.value.age)
    }

    @Test
    fun `OnUserChangeTermsAndConditions action should update ui state`() {
        val checked = true

        viewModel.onUserAction(LoginAction.OnUserChangeTermsAndConditions(checked))

        assertEquals(checked, viewModel.uiState.value.termsAndConditionsChecked)
    }

    @Test
    fun `canLogin is true when all form values are valid`() {
        fillFormWithCorrectValues()

        assertTrue(viewModel.uiState.value.canLogin)
    }

    private fun fillFormWithCorrectValues() {
        viewModel.onUserAction(LoginAction.OnUserNameChange("Carlos"))
        viewModel.onUserAction(LoginAction.OnUserEmailChange(StubEmailValidator.VALID_EMAIL))
        viewModel.onUserAction(LoginAction.OnUserPasswordChange("Password123-"))
        viewModel.onUserAction(LoginAction.OnUserAgeChange(20))
        viewModel.onUserAction(LoginAction.OnUserChangeTermsAndConditions(true))
    }

    @Test
    fun `canLogin is false when at least a value in form is invalid`() {
        fillFormWithCorrectValues()
        viewModel.onUserAction(LoginAction.OnUserAgeChange(17))

        assertFalse(viewModel.uiState.value.canLogin)
    }

    @Test
    fun `Login action updates uiState loginSuccess true when use case returns true`() = runTest {
        coEvery { loginUserUseCase.invoke(any(), any(), any()) } returns Result.Success(true)
        fillFormWithCorrectValues()

        viewModel.onUserAction(LoginAction.Login)
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.loginSuccess)
    }


    @Test
    fun `Login action updates uiState correctly`() = runTest {
        coEvery { loginUserUseCase.invoke(any(), any(), any()) } returns Result.Success(true)
        fillFormWithCorrectValues()

        viewModel.uiState.test {
            assertFalse(awaitItem().isLoading)
            viewModel.onUserAction(LoginAction.Login)
            assertTrue(awaitItem().isLoading)
            val uiStateResult = awaitItem()
            assertFalse(uiStateResult.isLoading)
            assertTrue(uiStateResult.loginSuccess)
        }

    }

}