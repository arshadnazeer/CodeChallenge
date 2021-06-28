package com.example.codingchallenge_paradox

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.codingchallenge_paradox.db.User
import com.example.codingchallenge_paradox.db.UserRepository
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.mockito.Mockito
import java.util.*

class UserViewModelTest{

    val fakeStr = "qwertyui"
    private lateinit var viewModel: UserViewModel
    private lateinit var repository: UserRepository


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = Mockito.mock(UserRepository::class.java)
        viewModel = UserViewModel(repository)
    }

    @Test
    fun fullNameUnderTest_input_checkParameters(){
        val result = viewModel.checkRegistrationInfo.value
        assertThat(result).isEqualTo(fakeStr)
    }
}