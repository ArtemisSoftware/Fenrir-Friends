package com.artemissoftware.fenrirfriends

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.BottomBarItem
import com.artemissoftware.fenrirfriends.base.FFBaseEventViewModel
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.navigation.mapper.toBottomBarItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(): FFBaseEventViewModel<MainEvents>() {

    val scaffoldState by lazy { FFUiScaffoldState() }

    init {
        getBottomNavigationItems()
    }

    private fun getBottomNavigationItems(){

        with(DestinationRoutes.HomeGraph){

            setBottomBarItems(listOf(gallery, breadSearch))
        }
    }


    private fun setBottomBarItems(items: List<BaseDestination>) {

        val bottomBarItems = mutableListOf<BottomBarItem>()

        with(DestinationRoutes.HomeGraph) {

            items.forEach {

                when (it) {

                    gallery -> {
                        bottomBarItems.add(
                            gallery.toBottomBarItem(
                                title = R.string.gallery,
                                icon = Icons.Rounded.Home
                            )
                        )
                    }
                    breadSearch -> {
                        bottomBarItems.add(
                            breadSearch.toBottomBarItem(
                                title = R.string.search,
                                icon = Icons.Rounded.Search
                            )
                        )
                    }
                    else -> {}
                }
            }

            scaffoldState.setBottomBarDestinations(bottomBarItems)
        }
    }


}