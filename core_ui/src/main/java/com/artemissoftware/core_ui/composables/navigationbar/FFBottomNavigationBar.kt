package com.artemissoftware.core_ui.composables.navigationbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.BottomBarItem
import com.artemissoftware.core_ui.theme.BottomGreen
import com.artemissoftware.core_ui.theme.TextNewRodin8

@Composable
fun FFBottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<BottomBarItem>,
    selectedDestination: BaseDestination,
    onClick: (BottomBarItem) -> Unit
) {

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
                    onClick(item)
                }
            )
        }
    }
}