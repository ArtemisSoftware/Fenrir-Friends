package com.artemissoftware.core_ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@get:Composable
val Colors.primaryText: Color
    get() = if (isLight) Color.Black else Color.White

@get:Composable
val Colors.secondaryBackground: Color
    get() = if (isLight) Color(0xffffffff) else Color(0xff252525)

val SuccessGreen = Color(0xFF4BCA81)
val ErrorRed = Color(0xFFCC3300)
val AlertYellow = Color(0xFFFFcc00)
val InfoBlue = Color(0xFF840B5ED)