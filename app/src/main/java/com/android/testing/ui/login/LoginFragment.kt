package com.android.testing.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.android.testing.R
import com.android.testing.databinding.FragmentLoginBinding
import com.android.testing.di.ModuleViewModel
import com.android.testing.di.ViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory(
            requireContext(),
            ModuleViewModel.LOGIN
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        collectUiState()
    }

    private fun setListeners() {
        binding.inputName.doOnTextChanged { text, _, _, _ ->
            viewModel.onUserAction(LoginAction.OnUserNameChange(text.toString()))
        }
        binding.inputPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.onUserAction(LoginAction.OnUserPasswordChange(text.toString()))
        }
        binding.inputEmail.doOnTextChanged { text, _, _, _ ->
            viewModel.onUserAction(LoginAction.OnUserEmailChange(text.toString()))
        }
        binding.inputAge.doOnTextChanged { text, _, _, _ ->
            viewModel.onUserAction(LoginAction.OnUserAgeChange(text.toString().toIntOrNull() ?: 0))
        }
        binding.checkboxTerms.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onUserAction(LoginAction.OnUserChangeTermsAndConditions(isChecked))
        }
        binding.btnCreateAccount.setOnClickListener {
            viewModel.onUserAction(LoginAction.Login)
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.uiState.collect(::handleUiState)
            }
        }
    }

    private fun handleUiState(uiState: LoginUiState) {
        binding.btnCreateAccount.isEnabled = uiState.canLogin
        if (uiState.loginSuccess) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.progressBar.isVisible = uiState.isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}