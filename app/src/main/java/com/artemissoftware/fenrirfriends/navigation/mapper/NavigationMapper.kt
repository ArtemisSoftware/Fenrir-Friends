package com.artemissoftware.fenrirfriends.navigation.mapper

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.BottomBarItem

fun BaseDestination.toBottomBarItem(
    @StringRes title: Int,
    icon: ImageVector
): BottomBarItem {

    return BottomBarItem(
        destination = this,
        title = title,
        icon = icon,
    )
}