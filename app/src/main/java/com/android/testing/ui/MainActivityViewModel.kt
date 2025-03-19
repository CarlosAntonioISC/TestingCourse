package com.android.testing.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.testing.domain.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val startDestination = flow {
        emit(userRepository.get() != null)
    }.map { userLogged ->
        if (userLogged) Destinations.HOME else Destinations.LOGIN
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

}