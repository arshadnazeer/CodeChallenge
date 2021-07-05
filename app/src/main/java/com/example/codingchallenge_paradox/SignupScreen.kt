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
import com.example.codingchallenge_paradox.databinding.FragmentSignupScreenBinding
import com.example.codingchallenge_paradox.db.UserDatabase
import com.example.codingchallenge_paradox.db.UserRepository
import com.example.codingchallenge_paradox.viewmodel.UserViewModel
import com.example.codingchallenge_paradox.viewmodel.UserViewModelFactory


class SignupScreen : Fragment() {

    private lateinit var binding: FragmentSignupScreenBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_signup_screen, container, false)

        val dao = context?.let { UserDatabase.getInstance(it).userDAO }
        val repository = dao?.let { UserRepository(it) }
        val factory = repository?.let { UserViewModelFactory(it) }
        viewModel = factory?.let { ViewModelProvider(this, it).get(UserViewModel::class.java) }!!

        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        registerUser()

        activity?.let {
            viewModel.showToastMessage.observe(it, Observer {
                if (!it.isNullOrEmpty()) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            })
        }
        return binding.root
    }

    private fun registerUser() {
        activity?.let {
            viewModel.users.observe(it, Observer {

            })
        }
        activity?.let {
            viewModel.checkRegistrationInfo.observe(it, Observer {
                val bundleUserName = bundleOf("input_name" to binding.textUsername.text.toString())
                findNavController().navigate(
                    R.id.action_signupScreen_to_homeScreen2,
                    bundleUserName
                )
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_LONG).show()
            })
        }

    }

}