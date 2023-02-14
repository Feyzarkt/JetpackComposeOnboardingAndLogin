package com.myapps.jetpackcomposeproject.ui.login

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
class LoginViewModel @Inject constructor(
    private val authServiceRepository: AuthServiceRepository
    ): ViewModel(){

    private val _loginUiState: MutableStateFlow<LoginUIState> =
        MutableStateFlow(LoginUIState.initial())
    val loginUiState: StateFlow<LoginUIState> = _loginUiState.asStateFlow()

    fun login(email: String, password: String){
        setLoadingState()
        authServiceRepository.login(email, password, object : IServiceListener<Boolean>{
            override fun onSuccess(successResult: Boolean) {
                setLoginState(successResult)
            }

            override fun onError(exception: Exception) {
                setErrorState(exception)
            }
        })
    }

    private fun setLoginState(status: Boolean) {
        _loginUiState.update { state ->
            state.copy(loginStatus = status, isLoading = false)
        }
    }

    fun loginStatusShown() {
        _loginUiState.update { state ->
            state.copy(loginStatus = false)
        }
    }

    private fun setLoadingState() {
        _loginUiState.update { state ->
            state.copy(isLoading = true)
        }
    }

    private fun setErrorState(exception: Exception) {
        _loginUiState.update { state ->
            state.copy(error = exception.toString(), isLoading = false)
        }
    }

}