package com.myapps.jetpackcomposeproject.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapps.jetpackcomposeproject.R
import com.myapps.jetpackcomposeproject.components.TextInput
import com.myapps.jetpackcomposeproject.data.model.InputType
import com.myapps.jetpackcomposeproject.data.model.Screen

@Composable
fun Login(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val loginUiState by viewModel.loginUiState.collectAsState()

    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val context = LocalContext.current

    if (loginUiState.loginStatus){
        Toast.makeText(context, "Successfully signed in.", Toast.LENGTH_SHORT ).show()
        viewModel.loginStatusShown()
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Login.route) {
                inclusive = true
            }
        }
    }
    if (loginUiState.error.isNotEmpty()){
        Toast.makeText(context, "ERROR: ${loginUiState.error}", Toast.LENGTH_SHORT ).show()
        loginUiState.error = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello Again!",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(bottom = 10.dp).align(Alignment.Start),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.purple_700)
        )
        Text(
            text = "Welcome",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 18.dp).align(Alignment.Start),
            color = colorResource(id = R.color.purple_700)
        )
        TextInput(InputType.Email, inputValue = emailValue, keyboardActions = KeyboardActions(onNext = {
            focusRequester.requestFocus()
        }))
        TextInput(InputType.Password, inputValue = passwordValue, keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }), focusRequester = focusRequester)
        Button(
            onClick = {
                if(emailValue.value.isEmpty() || passwordValue.value.isEmpty()){
                    Toast.makeText(context, "Please enter the necessary values!", Toast.LENGTH_SHORT ).show()
                }else{
                    viewModel.login(emailValue.value, passwordValue.value)
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(27.dp)
        ) {
            Text(text = "Sign In", modifier = Modifier.padding(vertical = 8.dp))
        }
        Divider(modifier = Modifier.fillMaxWidth())
        Row(modifier = Modifier.padding(bottom = 40.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Don\'t have an account?")
            TextButton(onClick = { navController.navigate(Screen.Signup.route) }) {
                Text(text = "Sign Up")
            }
        }
    }
}