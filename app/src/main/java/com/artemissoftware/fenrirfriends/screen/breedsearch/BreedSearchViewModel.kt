package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.lifecycle.viewModelScope
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.SearchBreedUseCase
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.base.FFBaseEventViewModel
import com.artemissoftware.fenrirfriends.base.events.UiEvent
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.screen.models.mappers.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BreedSearchViewModel @Inject constructor(
    private val searchBreedUseCase: SearchBreedUseCase
): FFBaseEventViewModel<BreedSearchEvents>() {

    private val _state: MutableStateFlow<BreedSearchState> = MutableStateFlow(BreedSearchState())
    val state: StateFlow<BreedSearchState> = _state


    override fun onTriggerEvent(event: BreedSearchEvents) {
        when(event){

            is BreedSearchEvents.GoToBreedDetail -> {
                sendUiEvent(UiEvent.Navigate(DestinationRoutes.DetailGraph.detailBreed.withCustomArgs(event.breed.toUI())))
            }
            is BreedSearchEvents.SearchBreed -> {
                searchBreeds(query = event.query)
            }
        }
    }

    private fun searchBreeds(query: String){

        searchBreedUseCase.invoke(query).onEach { result ->

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
                                title = "Search",
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