package com.example.codingchallenge_paradox.test

object Validator {
    fun validateInput(fullName: String, username: String, password: String) : Boolean{
        return  !(username.isEmpty() || fullName.isEmpty() || password.isEmpty())
    }

    fun validateFullName(fullName: String) : Boolean{
        return fullName.length > 5
    }

    fun validateUserName(username: String) : Boolean{
        return username.contains("_")
    }

    fun validatePassword(password: String) : Boolean{
        return password.length > 6
    }
}