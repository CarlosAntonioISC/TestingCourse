package com.android.testing.di

import android.content.Context
import com.android.testing.data.api.LoginApi
import com.android.testing.data.repository.RetrofitLoginRepository
import com.android.testing.data.repository.RoomUserRepository
import com.android.testing.domain.repository.LoginRepository
import com.android.testing.domain.repository.UserRepository

object RepositoryProvider {

    fun provideUserRepository(context: Context): UserRepository {
        return RoomUserRepository(userDao = DataProvider.provideDataBase(context).userDao())
    }

    fun provideLoginRepository(): LoginRepository {
        return RetrofitLoginRepository(DataProvider.provideApi(LoginApi::class.java))
    }

}