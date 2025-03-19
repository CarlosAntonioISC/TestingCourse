package com.android.testing.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.android.testing.di.ModuleViewModel
import com.android.testing.di.ViewModelFactory
import com.android.testing.ui.theme.TestingCourseTheme

class MainActivityCompose : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel> {
        ViewModelFactory(this, ModuleViewModel.MAIN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingCourseTheme {
                val startDestination = viewModel.startDestination.collectAsState()
                startDestination.value?.let { TestingCourseApp(startDestination = it) }
            }
        }
    }

}