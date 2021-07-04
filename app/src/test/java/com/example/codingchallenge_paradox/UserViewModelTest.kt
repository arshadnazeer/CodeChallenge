package com.example.codingchallenge_paradox

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

class UserViewModelTest{

    private var viewModel: UserViewModel? = null

    @Before
    fun setUp() {

        viewModel = Mockito.mock(UserViewModel::class.java)
        viewModel?.inputfullName?.value = "arshadnazeer"
        viewModel?.inputName?.value = "arshad"
        viewModel?.inputPassword?.value = "arshad123"
    }

    @Test
    fun fullNameUnderTest_input_checkParameters(){
        val value = viewModel?.register()
        Assert.assertTrue(value!!)
    }
}