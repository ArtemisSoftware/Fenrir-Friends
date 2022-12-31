package com.artemissoftware.core_ui.composables.dialog.models

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.artemissoftware.core_ui.theme.ErrorRed
import com.artemissoftware.core_ui.theme.InfoBlue
import com.artemissoftware.core_ui.theme.SuccessGreen

sealed class FFDialogType(
    val title: String,
    val description: String,
    val iconColor: Color,
    val mainColor: Color,
    @DrawableRes val imageId: Int? = null,
    val icon: ImageVector? = null,
    val dialogOptions: FFDialogOptions
){
    class Success(
        title: String,
        description: String,
        @DrawableRes imageId: Int? = null,
        icon: ImageVector? = null,
        dialogOptions: FFDialogOptions
    ) : FFDialogType(title = title, description = description, mainColor = SuccessGreen, iconColor = SuccessGreen, imageId = imageId, icon = icon, dialogOptions = dialogOptions)

    class Error(
        title: String,
        description: String,
        @DrawableRes imageId: Int? = null,
        icon: ImageVector? = null,
        dialogOptions: FFDialogOptions
    ) : FFDialogType(title = title, description = description, mainColor = ErrorRed, iconColor = ErrorRed, imageId = imageId, icon = icon, dialogOptions = dialogOptions)

    class Info(
        title: String,
        description: String,
        @DrawableRes imageId: Int? = null,
        icon: ImageVector? = null,
        dialogOptions: FFDialogOptions
    ) : FFDialogType(title = title, description = description, mainColor = InfoBlue, iconColor = InfoBlue, imageId = imageId, icon = Icons.Filled.Info, dialogOptions = dialogOptions)
}

