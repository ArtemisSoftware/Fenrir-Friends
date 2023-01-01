package com.artemissoftware.core_ui.composables.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.text.FFText

@Composable
fun FFTopBar(
    @DrawableRes backgroundId: Int = R.drawable.ic_top_app_bar_bg,
    content: @Composable () -> Unit,
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
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }

        content.invoke()
    }
}

@Preview(showBackground = true)
@Composable
private fun FFTopBarPreview() {

    FFTopBar {
        FFText(text = "Example")
    }
}