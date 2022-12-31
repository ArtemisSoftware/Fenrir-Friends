package com.artemissoftware.core_ui.composables.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R

@Composable
fun FFToolBar(
    @DrawableRes backgroundId: Int = R.drawable.ic_top_app_bar_bg,
    onBackClicked: (() -> Unit)? = null,
    toolbarActions: @Composable RowScope.() -> Unit = {},
    iconTint: Color = Color.Black
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
    ) {

        backgroundId?.let {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = it),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )
        }

        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            title = {},
            navigationIcon = {
                onBackClicked?.let {
                    FFToolbarAction(
                        imageVector = Icons.Filled.ArrowBack,
                        onClicked = it,
                        tint = iconTint
                    )
                }
            },
            actions = {
                toolbarActions()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FFToolBarPreview() {

    FFToolBar(
        toolbarActions = {
            FFToolbarAction(imageVector = Icons.Filled.Share)
            FFToolbarAction(imageVector = Icons.Filled.Settings)
        }
    )
}

