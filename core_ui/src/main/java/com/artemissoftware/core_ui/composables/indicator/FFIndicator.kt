package com.artemissoftware.core_ui.composables.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FFIndicator(
    color: Color = Color.Gray
) {

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(40.dp)
            .clip(CircleShape)
            .background(
                color.copy(alpha = 0.5f)
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun FFIndicatorPreview() {

    FFIndicator()
}