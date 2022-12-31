package com.artemissoftware.core_ui.composables.connectivity.models

sealed class FFConnectionState{
    object Available: FFConnectionState()
    object Unavailable: FFConnectionState()
}
