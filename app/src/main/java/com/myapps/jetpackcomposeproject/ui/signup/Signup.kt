package com.myapps.jetpackcomposeproject.ui.signup

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapps.jetpackcomposeproject.components.TextInput
import com.myapps.jetpackcomposeproject.data.model.InputType
import com.myapps.jetpackcomposeproject.data.model.Screen

@Composable
fun Signup(navController: NavController, viewModel: SignupViewModel = hiltViewModel()) {
    val signupUiState by viewModel.signupUiState.collectAsState()

    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val verifyCodeValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    val isValidEmail = emailValue.value.count() > 5 && '@' in emailValue.value
    val isValidPassword = passwordValue.value.count() > 7
    val isValidCode = verifyCodeValue.value.count() == 6
    var isValidProcess = false
    if (isValidEmail && isValidPassword && isValidCode) isValidProcess = true
    if (signupUiState.verifyCodeStatus) {
        Toast.makeText(context, "Verify code is sent.", Toast.LENGTH_SHORT ).show()
        viewModel.verifyCodeStatusShown()
    }
    if (signupUiState.registerStatus){
        Toast.makeText(context, "Successfully registered.", Toast.LENGTH_SHORT ).show()
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route){
                inclusive = true
            }
        }
    }
    if (signupUiState.error.isNotEmpty()){
        Toast.makeText(context, "ERROR: ${signupUiState.error}", Toast.LENGTH_SHORT ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(bottom = 18.dp)
        )
        TextInput(InputType.Email, inputValue = emailValue, keyboardActions = KeyboardActions(onGo = {
            focusRequester.requestFocus()
        }))
        TextInput(InputType.NewPassword, inputValue = passwordValue, keyboardActions = KeyboardActions(onNext = {
            focusRequester.requestFocus()
        }))
        TextInput(InputType.VerifyCode, inputValue = verifyCodeValue, keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }), focusRequester = focusRequester)
        TextButton(
            onClick = {
                Toast.makeText(context, "${emailValue.value} ${passwordValue.value}", Toast.LENGTH_SHORT ).show()
                viewModel.verifyEmail(emailValue.value)
                      },
            enabled = isValidEmail,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Get Code")
        }
        Button(
            onClick = {
                Toast.makeText(context, "${emailValue.value} ${passwordValue.value}", Toast.LENGTH_SHORT ).show()
                viewModel.registerUser(emailValue.value, passwordValue.value, verifyCodeValue.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            shape = RoundedCornerShape(27.dp),
            enabled = isValidProcess
        ) {
            Text(text = "Register", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}