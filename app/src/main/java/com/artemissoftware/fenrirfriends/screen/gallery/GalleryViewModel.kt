package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.paging.PagingData
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.data.errors.NetworkErrors.UNKNOWN_HOST
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.usecases.GetBreedsUseCase
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.base.FFBaseEventViewModel
import com.artemissoftware.fenrirfriends.base.events.UiEvent
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType
import com.artemissoftware.fenrirfriends.screen.models.mappers.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase
): FFBaseEventViewModel<GalleryEvents>() {

    private val _state = MutableStateFlow(GalleryState())
    val state: StateFlow<GalleryState> = _state
    val breeds: Flow<PagingData<Breed>> = getBreedsUseCase()


    override fun onTriggerEvent(event: GalleryEvents) {
        when(event){

            is GalleryEvents.GoToBreedDetail -> {
                sendUiEvent(UiEvent.Navigate(DestinationRoutes.DetailGraph.detailBreed.withArgs(breedId = event.breed.id)))
            }
            is GalleryEvents.ChangeView -> {

                _state.value = _state.value.copy(
                    viewType = if(_state.value.viewType == GalleryViewType.LIST) GalleryViewType.GRID else GalleryViewType.LIST
                )
            }
            is GalleryEvents.Reload -> {
                errorDialog(ex = event.ex, event.reloadEvent)
            }
            is GalleryEvents.ShowLoading -> {
                _state.value = _state.value.copy(
                    isLoading = event.loading
                )
            }
        }
    }

    private fun errorDialog(ex: FenrisFriendsNetworkException, reloadEvent: () -> Unit) {

        if(ex.code == UNKNOWN_HOST.first){
            sendUiEvent(
                UiEvent.ShowDialog(
                    FFDialogType.Info(
                        title = R.string.gallery,
                        description = ex.message ?: UNKNOWN_ERROR,
                        dialogOptions = FFDialogOptions(
                            confirmationTextId = R.string.retry,
                            confirmation = {
                                reloadEvent.invoke()
                            },
                            cancelTextId = R.string.cancel
                        )
                    )
                )
            )
        }
        else {

            sendUiEvent(
                UiEvent.ShowDialog(
                    FFDialogType.Error(
                        title = R.string.gallery,
                        description = ex.message ?: UNKNOWN_ERROR,
                        dialogOptions = FFDialogOptions(
                            confirmationTextId = R.string.ok,
                        )
                    )
                )
            )
        }
    }


}