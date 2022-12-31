package com.artemissoftware.core_ui.composables.toolbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun FFToolbarAction(
    imageVector: ImageVector,
    tint: Color = Color.Black,
    onClicked: () -> Unit = {}
) {

    IconButton(
        onClick = {
            onClicked()
        },
        content = {
            Icon(
                imageVector = imageVector,
                contentDescription = "icon",
                tint = tint
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun FFToolbarActionPreview() {

    FFToolbarAction(
        imageVector = Icons.Filled.Search,
        tint = Color.Black
    )

}


