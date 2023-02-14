package com.myapps.jetpackcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.huawei.agconnect.auth.AGConnectAuth
import com.myapps.jetpackcomposeproject.components.SetupNavGraph
import com.myapps.jetpackcomposeproject.data.model.Screen
import com.myapps.jetpackcomposeproject.ui.theme.JetpackComposeProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeProjectTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
                val user = AGConnectAuth.getInstance().currentUser
                if (user != null){
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}
