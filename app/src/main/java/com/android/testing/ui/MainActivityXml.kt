package com.android.testing.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.android.testing.R
import com.android.testing.databinding.ActivityMainBinding
import com.android.testing.di.ModuleViewModel
import com.android.testing.di.ViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivityXml : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel> {
        ViewModelFactory(this, ModuleViewModel.MAIN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collectUiState()
    }

    private fun collectUiState() {
        lifecycleScope.launch {
            val destination = viewModel.startDestination.first { it != null }
            handleDestination(destination)
        }
    }

    private fun handleDestination(destination: Destinations?) {
        val navController = findNavController(R.id.nav_host_fragment)
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph)
        when (destination) {
            Destinations.LOGIN -> graph.setStartDestination(R.id.loginFragment)
            Destinations.HOME -> graph.setStartDestination(R.id.homeFragment)
            else -> Unit
        }
        navController.graph = graph
    }

}
