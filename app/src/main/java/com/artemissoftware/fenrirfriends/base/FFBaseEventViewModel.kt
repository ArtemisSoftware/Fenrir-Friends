package com.artemissoftware.fenrirfriends.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents
import com.artemissoftware.fenrirfriends.base.events.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class FFBaseEventViewModel <E: FFBaseEvents> : ViewModel() {

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    open fun onTriggerEvent(event: E) {}

    protected fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    companion object{
        const val UNKNOWN_ERROR = "Unknown error"
    }
}