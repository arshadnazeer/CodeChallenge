package com.example.codingchallenge_paradox

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.codingchallenge_paradox.db.User
import com.example.codingchallenge_paradox.db.UserDAO
import com.example.codingchallenge_paradox.db.UserDatabase
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: UserDAO
    private lateinit var database: UserDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).build()

        dao = database.userDAO
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun saveUserTest() = runBlocking {
        val user = User(1,"arshadnazeer","arshad","arshad123")
        dao.insertUserDetails(user)

        val allUser = dao.getAllSubscribers()
        Truth.assertThat(allUser).isEqualTo(user)
    }
}