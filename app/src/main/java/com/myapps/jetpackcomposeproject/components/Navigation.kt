package com.myapps.jetpackcomposeproject.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myapps.jetpackcomposeproject.data.model.Screen
import com.myapps.jetpackcomposeproject.ui.home.Home
import com.myapps.jetpackcomposeproject.ui.login.Login
import com.myapps.jetpackcomposeproject.ui.signup.Signup

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Login.route) {
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