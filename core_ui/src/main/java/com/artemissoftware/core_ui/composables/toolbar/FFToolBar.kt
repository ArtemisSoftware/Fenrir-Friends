package com.artemissoftware.core_ui.composables.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R

@Composable
fun FFToolBar(
    @DrawableRes backgroundId: Int = R.drawable.ic_top_app_bar_bg,
    onBackClicked: (() -> Unit)? = null,
    toolbarActions: @Composable RowScope.(Color) -> Unit = {},
    iconColor: Color = Color.Black
) {

    FFTopBar(backgroundId = backgroundId) {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            title = {},
            navigationIcon = {
                onBackClicked?.let {
                    FFToolbarAction(
                        imageVector = Icons.Rounded.ArrowBack,
                        onClicked = it,
                        tint = iconColor
                    )
                }
            },
            actions = {
                toolbarActions(iconColor)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FFToolBarPreview() {

    FFToolBar(
        iconColor = Color.Green,
        onBackClicked = {},
        toolbarActions = { color->
            FFToolbarAction(imageVector = Icons.Filled.Share, tint = color)
            FFToolbarAction(imageVector = Icons.Filled.Settings)
        }
    )
}

