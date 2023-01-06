package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.toolbar.models.FFSearchToolBarState
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.composables.paging.HandlePagingResult
import com.artemissoftware.fenrirfriends.composables.toolbar.SearchToolbar
import com.artemissoftware.fenrirfriends.screen.breedsearch.composables.SearchResultsPlaceHolder
import com.artemissoftware.fenrirfriends.screen.breedsearch.composables.SearchStatisticsPlaceHolder
import com.artemissoftware.fenrirfriends.screen.gallery.composables.GalleryList


@Composable
fun BreedSearchScreen(
    viewModel: BreedSearchViewModel,
    windowSize: WindowSize
) {

    val state = viewModel.state.collectAsState()
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

    BuildBreedSearchScreen(
        state = state.value,
        events = viewModel::onTriggerEvent,
        searchAppBarState = viewModel.toolbarState.value,
        searchText = viewModel.searchText.value,
        searchResults = searchResults,
        windowSize = windowSize
    )
}


@Composable
private fun BuildBreedSearchScreen(
    state: BreedSearchState,
    searchText: String,
    searchAppBarState: FFSearchToolBarState,
    events: ((BreedSearchEvents) -> Unit)? = null,
    searchResults: LazyPagingItems<Breed>,
    windowSize: WindowSize
) {

    val listState = rememberLazyListState()

    FFScaffold(
        isLoading = state.isLoading,
        toolbar = {

            SearchToolbar(
                iconColor = Color.White,
                searchAppBarState = searchAppBarState,
                searchText = searchText,
                onTextChanged = {
                    events?.invoke(BreedSearchEvents.UpdateSearch(it))
                },
                onOpenClicked = {
                    events?.invoke(BreedSearchEvents.OpenSearch)
                },
                onCloseClicked = {
                    events?.invoke(BreedSearchEvents.CloseSearch)
                },
                onSearchClicked = {
                    events?.invoke(BreedSearchEvents.RepeatLastSearch)
                },
                placeholderTextId = R.string.search_by_breed_name,
            )
        },
        content =  {

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                SearchStatisticsPlaceHolder(
                    isSearching = state.isSearching,
                    numberOfResults = searchResults.itemCount
                )

                HandlePagingResult(
                    items = searchResults,
                    content = {
                        when{

                            searchResults.itemCount > 0->{

                                GalleryList(
                                    breeds = searchResults,
                                    onItemClick = {
                                        events?.invoke(BreedSearchEvents.GoToBreedDetail(it))
                                    },
                                    detailType = BreedDetailType.RESUME,
                                    windowSize = windowSize,
                                    listState = listState
                                )
                            }
                            searchAppBarState == FFSearchToolBarState.OPENED ->{
                                when{
                                    searchResults.itemCount == 0 && searchText.isBlank()->{
                                        SearchResultsPlaceHolder(messageId = R.string.start_your_search)
                                    }
                                    searchResults.itemCount == 0 && searchText.isNotBlank()->{
                                        SearchResultsPlaceHolder(messageId = R.string.no_results_try_different_search)
                                    }
                                }
                            }
                            else->{
                                SearchResultsPlaceHolder(messageId = R.string.start_your_search)
                            }
                        }
                    },
                    errorEvent = {
                        events?.invoke(BreedSearchEvents.Reload(
                            ex = it,
                            reloadEvent = {
                                events.invoke(BreedSearchEvents.RepeatLastSearch)
                            }
                        ))
                    }
                )
            }
        }
    )
}
