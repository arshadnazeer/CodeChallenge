package com.example.codingchallenge_paradox.db

class UserRepository(private val dao: UserDAO) {

    val allUsers = dao.getAllSubscribers()

    suspend fun insert(user: User){
        dao.insertUserDetails(user)
    }

}