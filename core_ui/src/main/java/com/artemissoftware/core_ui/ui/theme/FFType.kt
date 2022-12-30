package com.artemissoftware.core_ui.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.artemissoftware.core_ui.R


private val NewRodinFont = FontFamily(
    Font(R.font.new_rodin),
    Font(R.font.new_rodin, FontWeight.W500),
    Font(R.font.new_rodin, FontWeight.Bold)
)

val TextNewRodinFont = TextStyle(
    fontFamily = NewRodinFont,
    fontSize = 14.sp
)