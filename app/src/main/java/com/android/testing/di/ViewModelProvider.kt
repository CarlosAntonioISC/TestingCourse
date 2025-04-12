package com.android.testing.di

import android.content.Context
import com.android.testing.domain.AgeValidator
import com.android.testing.domain.AndroidEmailValidator
import com.android.testing.domain.EmailValidator
import com.android.testing.domain.NameValidator
import com.android.testing.domain.PasswordValidator
import com.android.testing.ui.MainActivityViewModel
import com.android.testing.ui.login.LoginViewModel

object ViewModelProvider {

    fun provideLogin(context: Context): LoginViewModel {
        return LoginViewModel(
            nameValidator = NameValidator(),
            ageValidator = AgeValidator(),
            emailValidator = AndroidEmailValidator(),
            passwordValidator = PasswordValidator(),
            loginUserUseCase = UseCaseProvider.provideLoginUser(context)
        )
    }

    fun provideMain(context: Context): MainActivityViewModel {
        return MainActivityViewModel(
            userRepository = RepositoryProvider.provideUserRepository(context)
        )
    }

}