package com.android.testing.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.testing.domain.repository.UserRepository
import com.android.testing.ui.login.LoginUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val uiState = flow {
        val user = userRepository.get()!!
        emit(HomeUiState(userName = user.name))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        HomeUiState()
    )

}