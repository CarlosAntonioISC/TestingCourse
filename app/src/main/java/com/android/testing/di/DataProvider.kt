package com.android.testing.di

import android.content.Context
import com.android.testing.data.database.AppDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {

    fun <Api> provideApi(
        apiInterface: Class<Api>,
    ): Api {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(apiInterface)
    }

    fun provideDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

}