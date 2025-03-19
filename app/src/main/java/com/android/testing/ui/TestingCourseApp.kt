package com.android.testing.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.testing.di.ModuleViewModel
import com.android.testing.di.ViewModelFactory
import com.android.testing.ui.login.LoginScreen
import com.android.testing.ui.login.LoginViewModel

enum class Destinations {
    LOGIN, HOME
}

@Composable
fun TestingCourseApp(startDestination: Destinations) {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination.name
    ) {

        composable(route = Destinations.LOGIN.name) {
            val viewModel = viewModel<LoginViewModel>(
                factory = ViewModelFactory(context, ModuleViewModel.LOGIN)
            )

            val uiState = viewModel.uiState.collectAsState().value

            LaunchedEffect(uiState.loginSuccess) {
                if (uiState.loginSuccess) navController.navigate(Destinations.HOME.name) {
                    popUpTo(navController.currentBackStackEntry?.destination?.route ?: "") {
                        inclusive = true
                    }
                }
            }

            LoginScreen(
                uiState = uiState,
                onUserAction = viewModel::onUserAction,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = Destinations.HOME.name) {
            Box(modifier = Modifier
                .background(Color.Red)
                .fillMaxSize())
        }

    }

}