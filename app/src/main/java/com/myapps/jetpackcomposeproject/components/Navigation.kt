package com.myapps.jetpackcomposeproject.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myapps.jetpackcomposeproject.components.model.Screen
import com.myapps.jetpackcomposeproject.ui.Login
import com.myapps.jetpackcomposeproject.ui.Signup

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            Login(navController)
        }
        composable(Screen.Signup.route) {
            Signup()
        }
    }
}