package com.artemissoftware.core_ui.composables.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun FFCircularIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.LocationOn,
    iconColor: Color = Color.Green,
    iconBackgroundColor: Color? = null,
    backgroundAlpha: Float? = null,
) {

    Icon(
        modifier = modifier
            .background(
                color = getBackgroundColor(iconColor = iconColor, iconBackgroundColor = iconBackgroundColor, backgroundAlpha = backgroundAlpha),
                shape = CircleShape
            )
            .padding(8.dp),
        imageVector = icon,
        tint = iconColor,
        contentDescription = "",
    )
}

@Composable
fun FFCircularIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.Build,
    iconColor: Color = Color.Green,
    iconBackgroundColor: Color? = null,
    backgroundAlpha: Float? = null,
    size: Dp = 40.dp,
    iconPadding: Dp = 8.dp,
){


    Box(
        modifier = modifier
            .background(
                color = getBackgroundColor(iconColor = iconColor, iconBackgroundColor = iconBackgroundColor, backgroundAlpha = backgroundAlpha),
                shape = CircleShape
            ).size(size)
    ) {


        Icon(
            modifier = Modifier.align(Alignment.Center)
                .size(size)
                .padding(iconPadding),
            imageVector = icon,
            tint = iconColor,
            contentDescription = "",
        )

    }


}


private fun getBackgroundColor(
    iconColor: Color = Color.Green,
    iconBackgroundColor: Color? = null,
    backgroundAlpha: Float? = null,
): Color {
    return when{

        iconBackgroundColor != null && backgroundAlpha != null ->{
            iconBackgroundColor.copy(alpha = backgroundAlpha)
        }

        iconBackgroundColor != null && backgroundAlpha == null ->{
            iconBackgroundColor
        }

        iconBackgroundColor == null && backgroundAlpha != null ->{
            iconColor.copy(alpha = backgroundAlpha)
        }

        else  ->{
            Color.Transparent
        }
    }
}

@Preview
@Composable
fun FFCircularIconPreview() {


    Column {
        FFCircularIcon(iconBackgroundColor = Color.Red, backgroundAlpha = 0.4F)
        FFCircularIcon(iconBackgroundColor = Color.Red)
        FFCircularIcon(backgroundAlpha = 0.1F)
        FFCircularIcon()
        FFCircularIcon(
            iconColor = Color.DarkGray,
            backgroundAlpha = 0.1F
        )
        FFCircularIcon(backgroundAlpha = 0.1F, modifier = Modifier.size(60.dp))
        FFCircularIcon(size = 40.dp, backgroundAlpha = 0.1F)
    }
}