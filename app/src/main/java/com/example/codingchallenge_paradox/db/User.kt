package com.example.codingchallenge_paradox.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="user_id")
        val id: Int,

        @ColumnInfo(name="user_full_name")
        val fullName: String,

        @ColumnInfo(name="user_name")
        val name: String,

        @ColumnInfo(name="user_password")
        val password: String
    )