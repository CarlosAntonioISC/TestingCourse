package com.android.testing.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.android.testing.data.database.AppDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {

    @VisibleForTesting
    val okHttpClient = OkHttpClient.Builder().build()
    @VisibleForTesting
    var baseTestingUrl: String? = null

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
        return AppDatabase.getInstance(context)
    }

}