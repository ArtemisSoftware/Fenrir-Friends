package com.artemissoftware.fenrirfriends.screen.breeddetail

import androidx.lifecycle.SavedStateHandle
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.base.FFBaseEventViewModel
import com.artemissoftware.fenrirfriends.base.events.UiEvent
import com.artemissoftware.fenrirfriends.navigation.NavigationArguments
import com.artemissoftware.fenrirfriends.screen.models.BreedUi
import com.artemissoftware.fenrirfriends.screen.models.mappers.toBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): FFBaseEventViewModel<BreedDetailEvents>(){

    private val _state: MutableStateFlow<BreedDetailState> = MutableStateFlow(BreedDetailState())
    val state: StateFlow<BreedDetailState> = _state


    init {

        val breedUi = savedStateHandle.get<BreedUi>(NavigationArguments.BREED_UI)

        breedUi?.let {
            _state.value = _state.value.copy(
                breed = it.toBreed()
            )
        } ?: run{
            showDialog(message = ERROR_LOADING_DETAIL)
        }

    }

    override fun onTriggerEvent(event: BreedDetailEvents) {
        when(event){

            is BreedDetailEvents.PopBackStack -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }


    private fun showDialog(message: String){

        sendUiEvent(
            UiEvent.ShowDialog(
                FFDialogType.Error(
                    title = "Detail",
                    description = message,
                    dialogOptions = FFDialogOptions(
                        confirmationTextId = R.string.ok,
                        confirmation = {
                            sendUiEvent(UiEvent.PopBackStack)
                        }
                    )
                )
            )
        )
    }


    companion object{
        private const val ERROR_LOADING_DETAIL = "Error loading detail"
    }

}