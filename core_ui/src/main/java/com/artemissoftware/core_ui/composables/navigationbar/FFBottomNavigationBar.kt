package com.artemissoftware.core_ui.composables.navigationbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.BottomBarItem
import com.artemissoftware.core_ui.theme.BottomGreen
import com.artemissoftware.core_ui.theme.TextNewRodin8

@Composable
fun FFBottomNavigationBar(
    @DrawableRes backgroundId: Int = R.drawable.ic_top_app_bar_bg,
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    items: List<BottomBarItem>,
    ffUiScaffoldState: FFUiScaffoldState? = null,
    onItemClick: (BottomBarItem) -> Unit,
    contentColor: Color = Color.White
) {

    navController?.let {

        if (items.isNotEmpty()) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            if (showBottomBar(currentDestination, items = items)) {

                ffUiScaffoldState?.let {

                    Box(modifier = Modifier) {
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(id = backgroundId),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )

                        BottomNavigation(
                            elevation = 0.dp,
                            modifier = modifier,
                            backgroundColor = Color.Transparent,
                        ) {

                            items.forEach { item ->

                                val isSelected =

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
                                            style = TextNewRodin8,
                                            color = contentColor
                                        )
                                    },
                                    selectedContentColor = contentColor,
                                    unselectedContentColor = contentColor.copy(0.4f),
                                    alwaysShowLabel = true,
                                    selected = isSelected(
                                        destination = item.destination,
                                        current = it.getSelectedBottomBarDestination(items[0].destination)
                                    ),
                                    onClick = {
                                        if (currentDestination?.route != item.destination.route) {
                                            onItemClick(item)
                                        }
                                    }
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

private fun isSelected(destination : BaseDestination, current: BaseDestination) : Boolean = destination.route == current.route

private fun showBottomBar(
    currentDestination: NavDestination?,
    items: List<BottomBarItem>) = items.any { it.destination.getRouteInFull() == currentDestination?.route }
