package com.example.codingchallenge_paradox

import com.example.codingchallenge_paradox.test.Validator
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid(){
        val fullName  = "userfullname"
        val username  = "user"
        val password  = "user123"

        val result = Validator.validateInput(fullName,username,password)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsInValid(){
        val fullName  = ""
        val username  = ""
        val password  = ""

        val result = Validator.validateInput(fullName,username,password)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenFullNameIsValid(){
        val fullName  = "userfullname"

        val result = Validator.validateFullName(fullName)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenFullNameIsInValid(){
        val fullName  = ""

        val result = Validator.validateFullName(fullName)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenUserNameeIsValid(){
        val username  = "username"

        val result = Validator.validateUserName(username)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenUserNameeIsInValid(){
        val username  = "_"

        val result = Validator.validateUserName(username)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenPasswordIsValid(){
        val password  = "user123"

        val result = Validator.validatePassword(password)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenPasswordIsInValid(){
        val password  = ""

        val result = Validator.validatePassword(password)
        assertThat(result).isEqualTo(false)
    }

}