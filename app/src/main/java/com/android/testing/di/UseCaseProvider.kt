package com.android.testing.di

import android.content.Context
import com.android.testing.data.PrintLogSender
import com.android.testing.domain.usecase.LoginUserUseCase

object UseCaseProvider {

    fun provideLoginUser(context: Context): LoginUserUseCase {
        return LoginUserUseCase(
            loginRepository = RepositoryProvider.provideLoginRepository(),
            userRepository = RepositoryProvider.provideUserRepository(context),
            logSender = PrintLogSender()
        )
    }

}