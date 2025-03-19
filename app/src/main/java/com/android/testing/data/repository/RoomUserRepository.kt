package com.android.testing.data.repository

import com.android.testing.data.database.UserDao
import com.android.testing.data.database.UserEntity
import com.android.testing.domain.User
import com.android.testing.domain.UserId
import com.android.testing.domain.repository.UserRepository

class RoomUserRepository(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun get(): User? {
        return userDao.get()?.let {
            User(
                id = UserId(it.id),
                name = it.name,
                email = it.email
            )
        }
    }

    override suspend fun save(user: User) {
        val userEntity = UserEntity(
            id = user.id.value,
            name = user.name,
            email = user.email
        )
        userDao.insert(userEntity)
    }

}