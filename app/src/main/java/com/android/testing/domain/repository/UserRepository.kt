package com.android.testing.domain.repository

import com.android.testing.domain.models.User

interface UserRepository {

    suspend fun get(): User?

    suspend fun save(user: User)

}