package com.azizutku.here

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.azizutku.here.databinding.ActivityMainBinding
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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

        authViewModel.login("azizutku@gmail.com", "12345678a")
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
                    Timber.i("Userr: ${dataState.data.user?.email}")
                }
                is DataState.Error -> {
                    // no op
                }
                is DataState.Loading -> {
                    // no op
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

}