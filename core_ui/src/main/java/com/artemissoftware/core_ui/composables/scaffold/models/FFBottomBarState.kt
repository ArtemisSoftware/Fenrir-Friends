package com.artemissoftware.core_ui.composables.scaffold.models

import com.artemissoftware.core_ui.navigation.models.BaseDestination

data class FFBottomBarState(
    private var currentPosition: Int = 0,
    var currentRoute: String? = null,
    var currentDestination: BaseDestination? = null
){

    fun update(currentPosition: Int, currentDestination: BaseDestination){
        this.currentPosition = currentPosition
        this.currentDestination = currentDestination
    }

}