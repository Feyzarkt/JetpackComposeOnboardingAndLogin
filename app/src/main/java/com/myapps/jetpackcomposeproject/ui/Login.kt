package com.myapps.jetpackcomposeproject.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapps.jetpackcomposeproject.components.TextInput
import com.myapps.jetpackcomposeproject.components.model.InputType
import com.myapps.jetpackcomposeproject.components.model.Screen

@Composable
fun Login(navController: NavController) {
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MyApp",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(bottom = 18.dp)
        )
        TextInput(InputType.Email, inputValue = emailValue, keyboardActions = KeyboardActions(onNext = {
            focusRequester.requestFocus()
        }))
        TextInput(InputType.Password, inputValue = passwordValue, keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }), focusRequester = focusRequester)
        Button(
            onClick = {
                Toast.makeText(context, "${emailValue.value} ${passwordValue.value}", Toast.LENGTH_SHORT ).show()
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(27.dp)
        ) {
            Text(text = "Login", modifier = Modifier.padding(vertical = 8.dp))
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