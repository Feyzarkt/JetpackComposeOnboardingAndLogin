package com.myapps.jetpackcomposeproject.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.unit.dp
import com.myapps.jetpackcomposeproject.data.model.InputType

@Composable
fun TextInput(
    inputType: InputType,
    inputValue: MutableState<String> = remember {
        mutableStateOf("")
    },
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusOrder(focusRequester ?: FocusRequester()),
        shape = RoundedCornerShape(27.dp),
        singleLine = true,
        label = { Text(text = inputType.label) },
        keyboardActions = keyboardActions,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        leadingIcon = { Icon(imageVector = inputType.icon, contentDescription = null) }
    )
}