package com.artemissoftware.fenrirfriends.navigation

import androidx.navigation.NavType
import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.CustomNavigationArgument
import com.artemissoftware.fenrirfriends.navigation.navtypes.BreedUiNavType
import com.artemissoftware.fenrirfriends.screen.models.BreedUi

sealed class Destination(
    route: String,
    customArguments: List<CustomNavigationArgument> = emptyList(),
) : BaseDestination(route = route, customArguments = customArguments){

    object Home : Destination(route = "HOME")
    object Gallery : Destination(route = "GALLERY")
    object BreedSearch : Destination(route = "BREED_SEARCH")


    object BreedDetail : Destination(
        route = "BREED_DETAIL",
        customArguments = listOf(
            CustomNavigationArgument(key = NavigationArguments.BREED_ID, type = NavType.IntType, nullable = false),
            CustomNavigationArgument(key = NavigationArguments.BREED_UI, type = BreedUiNavType(), nullable = true),
        )
    ){
        fun withArgs(breedId: Int = -1, breedUi: BreedUi? = null): String {

            with(NavigationArguments){

                val argumentBreedId = customArguments.find { it.key == BREED_ID }?.let {
                    "${it.key}=${breedId}"
                }

                val argumentBreedUi = customArguments.find { it.key == BREED_UI }?.let { argument->

                    breedUi?.let {
                        "${argument.key}=${convertCustomObject(it)}"
                    }
                }

                return addQueryToRoute(argumentBreedId, argumentBreedUi)
            }
        }
    }

}
