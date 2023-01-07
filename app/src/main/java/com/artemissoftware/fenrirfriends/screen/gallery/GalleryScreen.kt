package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.toolbar.FFToolBar
import com.artemissoftware.core_ui.composables.toolbar.FFToolbarAction
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.paging.HandlePagingResult
import com.artemissoftware.fenrirfriends.screen.breedsearch.composables.AnimatedPlaceHolder
import com.artemissoftware.fenrirfriends.screen.gallery.composables.BreedGallery
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType
import kotlinx.coroutines.launch

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel,
    windowSize: WindowSize
) {

    val state = viewModel.state.collectAsState()
    val breedsPagingItems: LazyPagingItems<Breed> = viewModel.breeds.collectAsLazyPagingItems()

    BuildGalleryScreen(
        state = state.value,
        events = viewModel::onTriggerEvent,
        pagingItems = breedsPagingItems,
        windowSize = windowSize
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BuildGalleryScreen(
    state: GalleryState,
    events: ((GalleryEvents) -> Unit)? = null,
    pagingItems: LazyPagingItems<Breed>,
    windowSize: WindowSize
) {

    val listState = rememberLazyListState()
    val gridState = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()

    FFScaffold(
        isLoading = state.isLoading,
        toolbar = {
            FFToolBar(
                iconColor = Color.White,
                toolbarActions = { iconColor->
                    ToolbarActions(
                        iconColor = iconColor,
                        galleryViewType = state.viewType,
                        onViewClicked = {

                            coroutineScope.launch {
                                if(state.viewType==GalleryViewType.LIST){
                                    gridState.scrollToItem(listState.firstVisibleItemIndex)
                                }
                                else{
                                    listState.scrollToItem(gridState.firstVisibleItemIndex)
                                }
                            }

                            events?.invoke(GalleryEvents.ChangeView)
                        },
                    )
                }
            )
        },
        content =  {

            HandlePagingResult(
                items = pagingItems,
                loading = {

                    events?.invoke(GalleryEvents.ShowLoading(it))
                },
                content = {

                    if(pagingItems.itemCount == 0){
                        AnimatedPlaceHolder(
                            lottie = R.raw.lottie_refresh,
                            messageId = R.string.click_to_reload,
                            modifier = Modifier.clickable {
                                pagingItems.refresh()
                            }
                        )
                    }
                    else {

                        BreedGallery(
                            listState = listState,
                            gridState = gridState,
                            windowSize = windowSize,
                            pagingItems = pagingItems,
                            state = state,
                            onItemClick = {
                                events?.invoke(GalleryEvents.GoToBreedDetail(it))
                            }
                        )
                    }
                },
                errorEvent = {
                    events?.invoke(GalleryEvents.Reload(
                        ex = it,
                        reloadEvent = { pagingItems.refresh() }
                    ))
                }

            )

        }
    )
}


@Composable
fun RowScope.ToolbarActions(
    onViewClicked: () -> Unit,
    galleryViewType: GalleryViewType,
    iconColor: Color
) {

        FFToolbarAction(
            imageVector = if(galleryViewType == GalleryViewType.LIST) Icons.Rounded.GridView else Icons.Rounded.ViewList,
            tint = iconColor,
            onClicked = { onViewClicked() }
        )

}




@Preview(showBackground = true)
@Composable
private fun BuildGalleryScreenPreview() {

    //val state = GalleryState(breeds = Breed.mockBreeds)
    //BuildGalleryScreen(state, events = {}, allHeroes)
}
