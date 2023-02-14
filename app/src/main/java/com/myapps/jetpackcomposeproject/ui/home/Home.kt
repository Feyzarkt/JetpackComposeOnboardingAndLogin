package com.myapps.jetpackcomposeproject.ui.home

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapps.jetpackcomposeproject.data.model.Screen

@Composable
fun Home(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    //val user = viewModel.auth.currentUser
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            Icons.Rounded.Logout,
            contentDescription = stringResource(id = R.string.cancel),
            modifier = Modifier.clickable {
                viewModel.auth.signOut()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MyApp \nHome Page",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(bottom = 18.dp),
            textAlign = TextAlign.Center
        )
        Divider(modifier = Modifier.fillMaxWidth())
        //Text(text = "Welcome ${user.email}")
    }
}