package com.artemissoftware.core_ui.composables.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.core_ui.theme.TextNewRodin

@Composable
fun FFTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    textStyle : TextStyle = TextNewRodin,
    singleLine: Boolean = true
) {

    val focusRequester = FocusRequester()

    TextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                onFocusChange.invoke(it)
            },
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = textStyle,
        placeholder = placeholder,
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors
    )
}

@Preview(showBackground = true)
@Composable
private fun FFTextFieldPreview() {

    FFTextField(
        value = "Artemis software - Fenrir friends"
    )
}