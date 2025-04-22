package com.android.testing.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import com.android.testing.ui.MainActivityViewModel
import com.android.testing.ui.home.HomeViewModel
import com.android.testing.ui.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
internal class ViewModelFactory(
    private val context: Context,
    private val moduleViewModel: ModuleViewModel
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (!modelClass.isAssignableFrom(moduleViewModel.clazz)) throw Exception()
        val appContext = context.applicationContext

        return when (moduleViewModel) {
            ModuleViewModel.LOGIN -> ViewModelProvider.provideLogin(appContext)
            ModuleViewModel.MAIN -> ViewModelProvider.provideMain(appContext)
            ModuleViewModel.HOME -> ViewModelProvider.provideHome(appContext)

        } as T
    }
}

internal enum class ModuleViewModel(val clazz: Class<*>) {
    LOGIN(LoginViewModel::class.java),
    HOME(HomeViewModel::class.java),
    MAIN(MainActivityViewModel::class.java)
}