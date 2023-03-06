package com.myapps.jetpackcomposeproject.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapps.jetpackcomposeproject.data.model.Screen
import com.myapps.jetpackcomposeproject.ui.home.Home
import com.myapps.jetpackcomposeproject.ui.login.Login
import com.myapps.jetpackcomposeproject.ui.onboarding.OnBoarding
import com.myapps.jetpackcomposeproject.ui.signup.Signup

@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.OnBoarding.route) {
        composable(Screen.OnBoarding.route) {
            OnBoarding(navController)
        }
        composable(Screen.Login.route) {
            Login(navController)
        }
        composable(Screen.Signup.route) {
            Signup(navController)
        }
        composable(Screen.Home.route) {
            Home(navController)
        }
    }
}