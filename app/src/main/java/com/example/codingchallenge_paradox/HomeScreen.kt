package com.example.codingchallenge_paradox

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.codingchallenge_paradox.databinding.FragmentHomeScreenBinding
import java.util.*

class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    var isActive = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
        binding.buttonLogout.setOnClickListener {

            //using Nav controller to traverse from one screen to another
            it.findNavController().navigate(R.id.action_homeScreen_to_loginScreen)
        }

        val inputUserName = requireArguments().getString("input_name")
        binding.textView.text = "Welcome <${inputUserName.toString()}>"
        return binding.root
    }


    override fun onPause() {
        super.onPause()
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launchWhenResumed {
                findNavController().navigate(R.id.action_homeScreen_to_loginScreen)
            }
            isActive = true
        }, 10000)
    }

    override fun onResume() {
        super.onResume()
        if (!isActive) {
            Handler(Looper.getMainLooper()).postDelayed({
                val startDestination = findNavController().graph.startDestination
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(startDestination, true)
                    .build()
                findNavController().navigate(startDestination, null, navOptions)

            }, 30000)
        }
    }

}