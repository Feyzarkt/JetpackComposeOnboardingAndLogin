package com.myapps.jetpackcomposeproject.components.model

sealed class Screen(val route: String){
    object Login: Screen(route = "login_screen")
    object Signup: Screen(route = "signup_screen")
}
