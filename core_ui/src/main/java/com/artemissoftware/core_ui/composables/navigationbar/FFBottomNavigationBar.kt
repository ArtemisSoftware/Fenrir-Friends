package com.artemissoftware.core_ui.composables.navigationbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.BottomBarItem
import com.artemissoftware.core_ui.theme.BottomGreen
import com.artemissoftware.core_ui.theme.TextNewRodin8

@Composable
fun FFBottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    items: List<BottomBarItem>,
    selectedDestination: BaseDestination,
    onItemClick: (BottomBarItem) -> Unit
) {

    navController?.let {

        if (items.isNotEmpty()) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            if (showBottomBar(currentDestination, items = items)) {

                BottomNavigation(
                    modifier = modifier,
                    backgroundColor = BottomGreen,
                    contentColor = Color.Black
                ) {

                    items.forEach { item ->

                        val isSelected = item.destination.route == selectedDestination.route

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = stringResource(id = item.title)
                                )
                            },
                            label = {
                                FFText(
                                    text = stringResource(id = item.title),
                                    style = TextNewRodin8
                                )
                            },
                            selectedContentColor = Color.Black,
                            unselectedContentColor = Color.Black.copy(0.4f),
                            alwaysShowLabel = true,
                            selected = isSelected,
                            onClick = {
                                onItemClick(item)
                            }
                        )
                    }
                }
            }
        }
    }
}


private fun showBottomBar(
    currentDestination: NavDestination?,
    items: List<BottomBarItem>) = items.any { it.destination.getRouteInFull() == currentDestination?.route }
