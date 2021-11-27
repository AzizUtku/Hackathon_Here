package com.azizutku.here

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.azizutku.here.databinding.ActivityMainBinding
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import android.view.WindowManager




@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val authViewModel: AuthViewModel by viewModels()

    var authResult: AuthResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        subscribeObservers()
        setupNavControllers()
        authViewModel.getUserFromCache()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }

    private fun subscribeObservers() {
        subscribeAuth()
    }

    private fun subscribeAuth() {
        authViewModel.dataStateUser.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<AuthResult> -> {
                    authResult = dataState.data
                }
                is DataState.Error -> {
                    navController.navigate(R.id.action_roomsFragment_to_signInFragment)
                }
                else -> {
                    // no op
                }
            }
        })
    }

    private fun setupNavControllers() {
        navController = findNavController(R.id.fragment).apply {
            addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    in listOf(R.id.roomsFragment, R.id.mapsFragment, R.id.browseFragment, R.id.profileFragment) -> {
                        binding.bottomNavigationView.visibility = View.VISIBLE
                        supportActionBar?.run {
                            setDisplayHomeAsUpEnabled(false)
                            setDisplayShowHomeEnabled(false)
                        }
                    }
                    else -> {
                        binding.bottomNavigationView.visibility = View.GONE
                        supportActionBar?.run {
                            setDisplayHomeAsUpEnabled(true)
                            setDisplayShowHomeEnabled(true)
                        }
                    }
                }
            }

        }
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun updateStatusBarColor(color: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

}