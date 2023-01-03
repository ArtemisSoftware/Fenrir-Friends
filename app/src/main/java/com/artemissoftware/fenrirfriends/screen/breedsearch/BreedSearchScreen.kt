package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                searchTextState = searchText,
                onTextChanged = {
                    events?.invoke(BreedSearchEvents.UpdateSearch(it))
                },
                onSearchClicked = {
                    events?.invoke(BreedSearchEvents.OpenSearch)
                },
                onCloseClicked = {
                    events?.invoke(BreedSearchEvents.CloseSearch)
                },
                placeholderTextId = R.string.search_by_breed_name,
            )
        },
        content =  {

            HandlePagingResult(
                items = searchResults,
                emptyContent = {
                    ResultsPlaceHolder(messageId = R.string.start_your_search)
                    //ResultsPlaceHolder(messageId = R.string.no_results_try_different_search)
                },
                loading = {
                    //events?.invoke(BreedSearchEvents.ShowLoading(it))
                },
                content = {
                    when{

                        searchResults.itemCount == 0 && searchText.isBlank() ->{
                            //--ResultsPlaceHolder(messageId = R.string.start_your_search)
                        }
                        searchResults.itemCount > 0 ->{

                            GalleryList(
                                breeds = searchResults,
                                onItemClick = {
                                    events?.invoke(BreedSearchEvents.GoToBreedDetail(it))
                                },
                                BreedDetailType.RESUME
                            )
                        }
                    }
                },
                errorContent = {
                    events?.invoke(BreedSearchEvents.Reload(ex = it, reloadEvent = {
                        events?.invoke(BreedSearchEvents.RepeatLastSearch)
                    }))
                }

            )




        }
    )
}

@Composable
private fun ResultsPlaceHolder(
    @StringRes messageId: Int
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.align(Alignment.Center),
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
fun RowScope.ToolbarActions(
    onReorderClicked: () -> Unit,
    onViewClicked: () -> Unit
) {
    FFToolbarAction(
        imageVector = Icons.Filled.MoreVert,
        tint = Color.White,
        onClicked = { onReorderClicked() }
    )
    FFToolbarAction(
        imageVector = Icons.Filled.Create,
        tint = Color.White,
        onClicked = { onViewClicked() }
    )

}


//@Preview(showBackground = true)
//@Composable
//private fun BuildBreedSearchScreenPreview() {
//
//    val state = BreedSearchState(breeds = Breed.mockBreeds)
////    BuildBreedSearchScreen(
////        state, searchText = "searchText",
////        searchAppBarState = FFSearchToolBarState.OPENED,
////        events = {},
////        searchResults
////    )
//}

@Preview(showBackground = true)
@Composable
private fun NoResultsFoundPreview() {

    ResultsPlaceHolder(messageId = R.string.name)
}
