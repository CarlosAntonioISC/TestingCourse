package com.android.testing.data.repository

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import com.android.testing.data.database.AppDatabase
import com.android.testing.domain.models.User
import com.android.testing.domain.models.UserId
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RoomUserRepositoryTest {

    private lateinit var database: AppDatabase
    private lateinit var sut: RoomUserRepository

    @Before
    fun setUp() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
            )
            .build()
        sut = RoomUserRepository(database.userDao())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun whenThereIsNoUserInTheDatabaseThenGetReturnsNull() = runTest {
        val user = sut.get()

        assertNull(user)
    }

    @Test
    fun whenUserIsSavedInTheDatabaseThenGetReturnsUser() = runTest {
        val user = User(UserId(1), "Carlos", "test@gmail.com")

        sut.save(user)

        assertEquals(user, sut.get())
    }

}