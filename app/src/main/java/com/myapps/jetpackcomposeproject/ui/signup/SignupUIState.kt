package com.myapps.jetpackcomposeproject.ui.signup

data class SignupUIState(
    val verifyCodeStatus: Boolean,
    val registerStatus: Boolean,
    val isLoading: Boolean,
    val error: String
) {
    companion object {
        fun initial() = SignupUIState(
            verifyCodeStatus = false,
            registerStatus = false,
            isLoading = false,
            error = ""
        )
    }
}