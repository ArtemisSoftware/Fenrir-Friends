package com.artemissoftware.core_ui.composables.toolbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.composables.textfield.FFTextField


@Composable
fun FFSearchToolBar(
    @DrawableRes backgroundId: Int = R.drawable.ic_top_app_bar_bg,
    text: String,
    @StringRes placeholderTextId: Int,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onFocusChange: (FocusState) -> Unit = {},
    onSearchClicked: (String) -> Unit,
    textColor: Color = Color.White,
    iconColor: Color = Color.White
) {

    var trailingIconState by remember {
        mutableStateOf(FFToolbarTrailingIconState.DELETE)
    }

    FFTopBar(backgroundId = backgroundId) {

        Surface(
            modifier = Modifier
                .fillMaxWidth().semantics {
                    contentDescription = "FFSearchToolBar"
                },
            color = Color.Transparent,
            elevation = 0.dp
        ) {

            FFTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "TextField"
                    }
                ,
                value = text,
                onFocusChange = onFocusChange,
                onValueChange = {

                    trailingIconState = if (it.isNotEmpty()) {
                        FFToolbarTrailingIconState.DELETE
                    } else {
                        FFToolbarTrailingIconState.CLOSE
                    }

                    onTextChange(it)
                },
                placeholder = {
                    FFText(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        text = stringResource(id = placeholderTextId),
                        color = Color.White
                    )
                },
                leadingIcon = {
                    FFToolbarAction(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        imageVector = Icons.Filled.Search,
                        tint = iconColor,
                        onClicked = {}
                    )
                },
                trailingIcon = {

                    FFToolbarAction(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .semantics {
                                contentDescription = "CloseButton"
                            },
                        imageVector = Icons.Filled.Close,
                        tint = iconColor,
                        onClicked = {

                            when (trailingIconState) {
                                FFToolbarTrailingIconState.DELETE -> {

                                    if (text.isNotEmpty()) {
                                        onTextChange("")
                                    }
                                    else{
                                        onCloseClicked()
                                        trailingIconState = FFToolbarTrailingIconState.CLOSE
                                    }
                                }
                                FFToolbarTrailingIconState.CLOSE -> {
                                    if (text.isNotEmpty()) {
                                        onTextChange("")
                                    } else {
                                        onCloseClicked()
                                        trailingIconState = FFToolbarTrailingIconState.DELETE
                                    }
                                }
                            }

                        }
                    )

                },
                keyboardOptions = KeyboardOptions(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    placeholderColor = textColor,
                    textColor = textColor,
                    cursorColor = textColor,
                    trailingIconColor = textColor,
                    leadingIconColor = textColor,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FFSearchToolBarPreview() {

    FFSearchToolBar(
        text = "Search text",
        placeholderTextId = R.string.confirm,
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}
