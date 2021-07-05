package com.example.codingchallenge_paradox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.codingchallenge_paradox.databinding.FragmentLoginScreenBinding
import com.example.codingchallenge_paradox.db.User
import com.example.codingchallenge_paradox.db.UserDatabase
import com.example.codingchallenge_paradox.db.UserRepository
import com.example.codingchallenge_paradox.viewmodel.UserViewModel
import com.example.codingchallenge_paradox.viewmodel.UserViewModelFactory

class LoginScreen : Fragment() {

    private lateinit var binding: FragmentLoginScreenBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login_screen, container, false)
        val dao = context?.let { UserDatabase.getInstance(it).userDAO }
        val repository = dao?.let { UserRepository(it) }
        val factory = repository?.let { UserViewModelFactory(it) }
        viewModel = factory?.let { ViewModelProvider(this, it).get(UserViewModel::class.java) }!!

        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        displaySubscribersList()

        activity?.let {
            viewModel.showToastMessage.observe(it, Observer {
                if (!it.isNullOrEmpty()) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            })
        }

        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreen_to_signupScreen)
        }

        return binding.root
    }

    private fun displaySubscribersList() {
        activity?.let {
            viewModel.users.observe(it, Observer {
                val subscribers: List<User> = it
            })
        }

        activity?.let {
            viewModel.checkUsers.observe(it, Observer {
                val subscribers: List<User> = it
                var userContains = false
                for (subs in subscribers) {
                    if (subs.name.equals(viewModel.inputName.value) && subs.password.equals(
                            viewModel.inputPassword.value
                        )
                    ) {
                        userContains = true
                    }
                }

                if (userContains) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                    val bundleUserName =
                        bundleOf("input_name" to binding.usernameText.text.toString())
                    findNavController().navigate(
                        R.id.action_loginScreen_to_homeScreen2,
                        bundleUserName
                    )
                } else {
                    Toast.makeText(
                        context,
                        "User not registered. Please sign up!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

}