package com.android.testing.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.android.testing.databinding.ActivityMainBinding
import com.android.testing.di.ModuleViewModel
import com.android.testing.di.ViewModelFactory
import com.android.testing.ui.home.HomeFragment
import com.android.testing.ui.login.LoginFragment
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.startDestination.collect {
                    handleDestination(it)
                }
            }
        }
    }

    private fun handleDestination(destination: Destinations?) {


    }

}
