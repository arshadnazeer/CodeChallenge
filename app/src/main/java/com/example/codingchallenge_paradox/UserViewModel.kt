package com.example.codingchallenge_paradox

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge_paradox.db.User
import com.example.codingchallenge_paradox.db.UserRepository
import kotlinx.coroutines.launch

open class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    var users = userRepository.allUsers
    val showToastMessage = MutableLiveData<String>()
    var checkUsers = MutableLiveData<List<User>>()
    var checkRegistrationInfo = MutableLiveData<List<User>>()

    val inputfullName = MutableLiveData<String>()
    val inputName = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()

    fun insert(user: User) {
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }

    fun login(){
        val name: String = inputName.value.toString()
        val password: String = inputPassword.value.toString()

        if (!name.equals("null") && name.length > 2) {
            if (!password.equals("null") && password.length > 5) {
                checkUsers.postValue(users.value)
            }
            else {
                showToastMessage.postValue("Password should not be less than 5 characters")
            }
        } else {
            showToastMessage.postValue("User name should not be empty")

        }

    }

    fun register() : Boolean{
        val name: String = inputName.value.toString()
        val password: String = inputPassword.value.toString()
        val fullName: String = inputfullName.value.toString()
        insert(User(0, fullName, name, password))

        if (!fullName.equals("null") && fullName.length > 5) {
            if (!name.equals("null") && name.length > 2) {
                if (!password.equals("null") && password.length > 5) {
                    checkRegistrationInfo.postValue(users.value)
                    return true
                }
                else {
                    showToastMessage.postValue("Password should not be less than 5 characters")
                }
            } else {
                showToastMessage.postValue("User name should not be empty")
            }
        } else {
            showToastMessage.postValue("Full name should not be empty")
        }
        return false
    }
}