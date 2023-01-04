package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.core_ui.composables.toolbar.models.FFSearchToolBarState
import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.data.errors.NetworkErrors
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.usecases.SearchBreedUseCase
import com.artemissoftware.fenrirfriends.BuildConfig.SEARCH_DELAY
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.base.FFBaseEventViewModel
import com.artemissoftware.fenrirfriends.base.events.UiEvent
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.screen.models.mappers.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedSearchViewModel @Inject constructor(
    private val searchBreedUseCase: SearchBreedUseCase
): FFBaseEventViewModel<BreedSearchEvents>() {

    private val _state: MutableStateFlow<BreedSearchState> = MutableStateFlow(BreedSearchState())
    val state: StateFlow<BreedSearchState> = _state

    var toolbarState: MutableState<FFSearchToolBarState> = mutableStateOf(FFSearchToolBarState.CLOSED)
        private set

    var searchText: MutableState<String> = mutableStateOf("")
        private set

    private var searchJob: Job? = null

    private val _searchResults = MutableStateFlow<PagingData<Breed>>(PagingData.empty())
    val searchResults = _searchResults


    override fun onTriggerEvent(event: BreedSearchEvents) {
        when(event){

            is BreedSearchEvents.GoToBreedDetail -> {
                sendUiEvent(UiEvent.Navigate(DestinationRoutes.DetailGraph.detailBreed.withArgs(breedUi = event.breed.toUI())))
            }
            is BreedSearchEvents.SearchBreed -> {
                searchBreeds(query = event.query)
            }
            BreedSearchEvents.OpenSearch -> {
                toolbarState.value = FFSearchToolBarState.OPENED
            }
            BreedSearchEvents.CloseSearch -> {
                toolbarState.value = FFSearchToolBarState.CLOSED
                searchText.value = ""
            }
            is BreedSearchEvents.UpdateSearch -> {
                searchText.value = event.text
                searchBreeds(query = event.text)
            }
            is BreedSearchEvents.Reload -> {
                errorDialog(ex = event.ex, event.reloadEvent)
            }
            is BreedSearchEvents.ShowLoading -> {
                _state.value = _state.value.copy(
                    isLoading = event.loading
                )
            }
            BreedSearchEvents.RepeatLastSearch -> {
                searchBreeds(query = searchText.value)
            }
        }
    }


    private fun searchBreeds(query: String) {

        searchJob?.cancel()

        if(query.isBlank()) {
            return
        }


        searchJob = viewModelScope.launch {

            delay(SEARCH_DELAY)

            _state.value = _state.value.copy(
                isSearching = true
            )

            searchBreedUseCase.invoke(query).cachedIn(viewModelScope).collect {

                delay(500)
                _state.value = _state.value.copy(
                    isSearching = false
                )
                _searchResults.value = it
            }
        }
    }

    private fun errorDialog(ex: FenrisFriendsNetworkException, reloadEvent: () -> Unit) {

        if(ex.code == NetworkErrors.UNKNOWN_HOST.first){
            sendUiEvent(
                UiEvent.ShowDialog(
                    FFDialogType.Info(
                        title = "Gallery",
                        description = ex.message ?: "Unknown error",
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
                        title = "Gallery",
                        description = ex.message ?: "Unknown error",
                        dialogOptions = FFDialogOptions(
                            confirmationTextId = R.string.ok,
                        )
                    )
                )
            )
        }
    }

}