package com.example.codingchallenge_paradox

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codingchallenge_paradox.db.User
import com.example.codingchallenge_paradox.db.UserRepository
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import java.util.*
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var initRule:MockitoRule = MockitoJUnit.rule()
    private val application = Mockito.mock(Application::class.java)

    private val userFakeRepository : UserRepository = Mockito.mock(UserRepository::class.java)
    private var viewModel: UserViewModel? = null

    private lateinit var user : ArrayList<User>

    @Before
    fun setUp() {
        user = ArrayList()
        user.add(User(1,"arshadnazeer","arshad","arshad123"))

        Mockito.doReturn(user).`when`(userFakeRepository).allUsers
        viewModel = UserViewModel(userFakeRepository) // need to be checked
    }

    @Test
    fun fullNameUnderTest_input_checkParameters(){

        val user1 = User(1,"arshadnazeer","arshad","arshad123")
        Assert.assertEquals(user1.name,"arshad")
    }
}