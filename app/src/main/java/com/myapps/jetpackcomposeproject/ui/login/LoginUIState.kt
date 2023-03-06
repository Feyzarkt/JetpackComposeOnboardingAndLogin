package com.myapps.jetpackcomposeproject.ui.login

data class LoginUIState(
    val loginStatus: Boolean,
    val isLoading: Boolean,
    var error: String
) {
    companion object {
        fun initial() = LoginUIState(
            loginStatus = false,
            isLoading = false,
            error = ""
        )
    }
}