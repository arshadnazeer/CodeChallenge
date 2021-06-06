package com.example.codingchallenge_paradox.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUserDetails(user: User)

    @Query("SELECT * FROM user_data_table")
    fun getAllSubscribers(): LiveData<List<User>>
}