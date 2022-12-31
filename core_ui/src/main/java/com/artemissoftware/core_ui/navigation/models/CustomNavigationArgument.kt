package com.artemissoftware.core_ui.navigation.models

import androidx.navigation.NavType

data class CustomNavigationArgument(
    val key: String,
    val type: NavType<*> = NavType.StringType,
    val nullable: Boolean = true
)