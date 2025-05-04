package com.android.testing.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.android.testing.data.database.AppDatabase
import com.android.testing.data.database.TestDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {

    @VisibleForTesting
    val okHttpClient = OkHttpClient.Builder().build()
    @VisibleForTesting
    var baseTestingUrl: String? = null
    @VisibleForTesting
    var isTestingEnvironment = false

    fun <Api> provideApi(
        apiInterface: Class<Api>,
    ): Api {
        return Retrofit.Builder()
            .baseUrl(baseTestingUrl ?: "https://fakestoreapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(apiInterface)
    }

    fun provideDataBase(context: Context): AppDatabase {
        if (isTestingEnvironment) return TestDatabase.getInstance(context)
        return AppDatabase.getInstance(context)
    }

}