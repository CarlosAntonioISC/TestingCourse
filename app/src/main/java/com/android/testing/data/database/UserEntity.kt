package com.android.testing.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String
)