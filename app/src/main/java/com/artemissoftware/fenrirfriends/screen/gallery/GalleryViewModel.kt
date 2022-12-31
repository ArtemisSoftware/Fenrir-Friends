package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.lifecycle.viewModelScope
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetBreedsUseCase
import com.artemissoftware.fenrirfriends.BuildConfig.ITEMS_PER_PAGE
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.base.FFBaseEventViewModel
import com.artemissoftware.fenrirfriends.base.events.UiEvent
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType
import com.artemissoftware.fenrirfriends.screen.models.mappers.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase
): FFBaseEventViewModel<GalleryEvents>() {

    private val _state = MutableStateFlow(GalleryState())
    val state: StateFlow<GalleryState> = _state

    init {
        getBreeds()
    }

    override fun onTriggerEvent(event: GalleryEvents) {
        when(event){

            is GalleryEvents.GoToBreedDetail -> {
                sendUiEvent(UiEvent.Navigate(DestinationRoutes.DetailGraph.detailBreed.withCustomArgs(event.breed.toUI())))
            }
            is GalleryEvents.ReorderAlphabetic -> {
                //--sendUiEvent(UiEvent.Navigate(DestinationRoutes.GalleryGraph.pictures.withCustomArgs(event.breed.toUI())))
            }
            is GalleryEvents.ChangeView -> {

                _state.value = _state.value.copy(
                    viewType = if(_state.value.viewType == GalleryViewType.LIST) GalleryViewType.GRID else GalleryViewType.LIST
                )
            }
        }
    }

    private fun getBreeds(){

        getBreedsUseCase.invoke(limit = ITEMS_PER_PAGE, page = 1).onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        breeds = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        breeds = result.data ?: emptyList(),
                        isLoading = false
                    )

                    sendUiEvent(
                        UiEvent.ShowDialog(
                            FFDialogType.Error(
                                title = "Gallery",
                                description = result.message ?: "Unknown error",
                                dialogOptions = FFDialogOptions(
                                    confirmationTextId = R.string.ok,
                                )
                            )
                        )
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)


    }
}