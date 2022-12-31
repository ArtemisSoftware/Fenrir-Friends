package com.artemissoftware.fenrirfriends.navigation

import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.CustomNavigationArgument
import com.artemissoftware.fenrirfriends.navigation.navtypes.BreedUiNavType

sealed class Destination(
    route: String,
    customArguments: List<CustomNavigationArgument> = emptyList(),
) : BaseDestination(route = route, customArguments = customArguments){

    object Home : Destination(route = "HOME")
    object Gallery : Destination(route = "GALLERY")
    object BreedDetail : Destination(route = "BREED_DETAIL", customArguments = listOf(CustomNavigationArgument(key = NavigationArguments.BREED_UI, type = BreedUiNavType())))
    object BreedSearch : Destination(route = "BREED_SEARCH")

}
