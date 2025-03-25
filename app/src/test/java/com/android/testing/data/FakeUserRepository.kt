package com.android.testing.data

import com.android.testing.domain.models.User
import com.android.testing.domain.repository.UserRepository

class FakeUserRepository: UserRepository {

    private var user: User? = null

    override suspend fun get(): User? = user

    override suspend fun save(user: User) {
        this.user = user
    }

}