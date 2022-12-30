package com.artemissoftware.core_ui.composables.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.artemissoftware.core_ui.theme.TextNewRodin
import com.artemissoftware.core_ui.theme.TextNewRodin12
import com.artemissoftware.core_ui.theme.TextNewRodinBold
import com.artemissoftware.core_ui.theme.primaryText


@Composable
fun FFText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primaryText,
    textAlign: TextAlign? = null,
    style: TextStyle = TextNewRodin,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {

    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        style = style,
        letterSpacing = letterSpacing,
        overflow = overflow,
        maxLines = maxLines
    )
}

@Preview
@Composable
private fun FFTextPreview() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FFText(
            text = "Fenrir Friends - TextNewRodin",
            style = TextNewRodin
        )

        FFText(
            text = "Fenrir Friends - TextNewRodinBold",
            style = TextNewRodinBold
        )

        FFText(
            text = "Fenrir Friends - TextNewRodin12",
            style = TextNewRodin12
        )

    }

}