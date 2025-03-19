package com.android.testing.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity LIMIT 1")
    suspend fun get(): UserEntity?

    @Insert
    suspend fun insert(user: UserEntity)

}