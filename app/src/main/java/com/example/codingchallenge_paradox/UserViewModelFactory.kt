package com.example.codingchallenge_paradox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codingchallenge_paradox.db.UserRepository
import java.lang.IllegalArgumentException

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}