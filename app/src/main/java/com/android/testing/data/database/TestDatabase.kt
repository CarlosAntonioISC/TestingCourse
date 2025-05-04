package com.android.testing.data.database

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

internal object TestDatabase {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .setTransactionExecutor(Dispatchers.Main.asExecutor())
                .allowMainThreadQueries()
                .build()
            INSTANCE!!
        }
    }

}