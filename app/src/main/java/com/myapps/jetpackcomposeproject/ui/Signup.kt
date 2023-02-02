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
import com.myapps.jetpackcomposeproject.components.TextInput
import com.myapps.jetpackcomposeproject.components.model.InputType

@Preview(showBackground = true)
@Composable
fun Signup() {
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val verifyCodeValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    val isValidEmail = emailValue.value.count() > 5 && '@' in emailValue.value
    val isValidPassword = passwordValue.value.count() > 5
    val isValidCode = verifyCodeValue.value.count() == 6
    var isValidProcess = false
    if (isValidEmail && isValidPassword && isValidCode) isValidProcess = true

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
        TextButton(onClick = { /*TODO*/ }, enabled = isValidEmail, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Get Code")
        }
        Button(
            onClick = {
                Toast.makeText(context, "${emailValue.value} ${passwordValue.value}", Toast.LENGTH_SHORT ).show()
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