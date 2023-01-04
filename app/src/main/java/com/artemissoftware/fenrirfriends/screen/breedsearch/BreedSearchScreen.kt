package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.core_ui.composables.animations.lottie.FFLottieLoader
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.composables.toolbar.FFToolbarAction
import com.artemissoftware.core_ui.composables.toolbar.models.FFSearchToolBarState
import com.artemissoftware.core_ui.theme.TextNewRodin14
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.composables.paging.HandlePagingResult
import com.artemissoftware.fenrirfriends.composables.toolbar.SearchToolbar
import com.artemissoftware.fenrirfriends.screen.gallery.composables.GalleryList


@Composable
fun BreedSearchScreen(
    viewModel: BreedSearchViewModel
) {

    val state = viewModel.state.collectAsState()
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

    BuildBreedSearchScreen(
        state = state.value,
        events = viewModel::onTriggerEvent,
        searchAppBarState = viewModel.toolbarState.value,
        searchText = viewModel.searchText.value,
        searchResults = searchResults
    )
}


@Composable
private fun BuildBreedSearchScreen(
    state: BreedSearchState,
    searchText: String,
    searchAppBarState: FFSearchToolBarState,
    events: ((BreedSearchEvents) -> Unit)? = null,
    searchResults: LazyPagingItems<Breed>
) {

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

            HandlePagingResult(
                items = searchResults,
                content = {
                    when{

                        searchResults.itemCount > 0->{

                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {

                                SearchResultsPlaceHolder(
                                    state = state,
                                    returnedValues = stringResource(R.string.results, searchResults.itemCount)
                                )

                                GalleryList(
                                    breeds = searchResults,
                                    onItemClick = {
                                        events?.invoke(BreedSearchEvents.GoToBreedDetail(it))
                                    },
                                    BreedDetailType.RESUME
                                )
                            }


                        }
                        searchAppBarState == FFSearchToolBarState.OPENED ->{
                            when{
                                searchResults.itemCount == 0 && searchText.isBlank()->{
                                    ResultsPlaceHolder(messageId = R.string.start_your_search)
                                }
                                searchResults.itemCount == 0 && searchText.isNotBlank()->{
                                    ResultsPlaceHolder(messageId = R.string.no_results_try_different_search)
                                }
                            }
                        }
                        else->{
                            ResultsPlaceHolder(messageId = R.string.start_your_search)
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
    )
}

@Composable
private fun ResultsPlaceHolder(
    @StringRes messageId: Int
) {

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1F else 0f,
        animationSpec = tween(
            durationMillis = 4500
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(alpha = alphaAnim),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            FFLottieLoader(
                id = R.raw.lottie_data_not_found,
                modifier = Modifier.size(200.dp)
            )
            FFText(
                text = stringResource(messageId),
                style = TextNewRodin14
            )
        }
    }
}

@Composable
private fun SearchResultsPlaceHolder(
    state: BreedSearchState,
    returnedValues: String
) {
    Box(modifier = Modifier.fillMaxWidth()) {

        if (state.isSearching) {
            FFLottieLoader(
                id = com.artemissoftware.core_ui.R.raw.lottie_fenris,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterStart)
                    .padding(0.dp)
            )
        }
        FFText(
            style = TextNewRodin14,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(12.dp),
            text = returnedValues
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultsPlaceHolderPreview() {

    ResultsPlaceHolder(messageId = R.string.name)
}

@Preview(showBackground = true)
@Composable
private fun SearchResultsPlaceHolderPreview() {

    val state = BreedSearchState(isSearching = true)

    SearchResultsPlaceHolder(
        state = state,
        returnedValues = "0 results"
    )
}
