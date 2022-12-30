package com.artemissoftware.core_ui.composables.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.text.FFText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FFCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    elevation: Dp = 12.dp,
    shape: Shape = RoundedCornerShape(4.dp),
    content: @Composable () -> Unit
) {

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        onClick = onClick,
        elevation = elevation,
        shape = shape,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun FFCardPreview() {

    FFCard{
        FFText(text = "Fenrir Friends")
    }

}