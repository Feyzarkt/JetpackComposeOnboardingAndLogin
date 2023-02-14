package com.myapps.jetpackcomposeproject.ui.signup

import androidx.lifecycle.ViewModel
import com.myapps.jetpackcomposeproject.data.repository.AuthServiceRepository
import com.myapps.jetpackcomposeproject.util.IServiceListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authServiceRepository: AuthServiceRepository
    ): ViewModel(){

    private val _signupUiState: MutableStateFlow<SignupUIState> =
        MutableStateFlow(SignupUIState.initial())
    val signupUiState: StateFlow<SignupUIState> = _signupUiState.asStateFlow()

    fun verifyEmail(email: String){
        setLoadingState()
        authServiceRepository.verifyEmail(email, object : IServiceListener<Boolean>{
            override fun onSuccess(successResult: Boolean) {
                setVerifyCodeState(successResult)
            }

            override fun onError(exception: Exception) {
                setErrorState(exception)
            }
        })
    }

    fun registerUser(email: String, password: String, code: String){
        setLoadingState()
        authServiceRepository.registerUser(email, password, code, object : IServiceListener<Boolean>{
            override fun onSuccess(successResult: Boolean) {
                setRegisterState(successResult)
            }

            override fun onError(exception: Exception) {
                setErrorState(exception)
            }
        })
    }

    private fun setVerifyCodeState(status: Boolean) {
        _signupUiState.update { state ->
            state.copy(verifyCodeStatus = status, isLoading = false)
        }
    }

    fun verifyCodeStatusShown() {
        _signupUiState.update { state ->
            state.copy(verifyCodeStatus = false)
        }
    }

    private fun setRegisterState(status: Boolean) {
        _signupUiState.update { state ->
            state.copy(registerStatus = status, isLoading = false)
        }
    }

    private fun setLoadingState() {
        _signupUiState.update { state ->
            state.copy(isLoading = true)
        }
    }

    private fun setErrorState(exception: Exception) {
        _signupUiState.update { state ->
            state.copy(error = exception.toString(), isLoading = false)
        }
    }

}