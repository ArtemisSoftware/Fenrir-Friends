package com.artemissoftware.fenrirfriends.navigation

import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.CustomNavigationArgument

sealed class Destination(
    route: String,
    customArguments: List<CustomNavigationArgument> = emptyList(),
) : BaseDestination(route = route, customArguments = customArguments){

    object Gallery : Destination(route = "GALLERY")
    object BreedDetail : Destination(route = "BREED_DETAIL", customArguments = listOf(CustomNavigationArgument(NavigationArguments.BREED_UI)))

}
