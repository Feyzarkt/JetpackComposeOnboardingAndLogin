package com.myapps.jetpackcomposeproject.data.model

sealed class Screen(val route: String){
    object OnBoarding: Screen(route = "onboarding_screen")
    object Login: Screen(route = "login_screen")
    object Signup: Screen(route = "signup_screen")
    object Home: Screen(route = "home_screen")
}
