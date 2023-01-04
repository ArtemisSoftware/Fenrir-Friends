package com.artemissoftware.fenrirfriends.composables.paging

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.data.errors.FenrisFriendsNetworkException

@Composable
fun <T: Any>HandlePagingResult(
    items: LazyPagingItems<T>,
    loading: (Boolean) -> Unit,
    emptyContent: @Composable () -> Unit = {},
    errorContent: @Composable (FenrisFriendsNetworkException) -> Unit,
    content: @Composable () -> Unit
) {

    items.apply {

        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        content()
        loading.invoke((loadState.refresh is LoadState.Loading) && items.itemCount < 1)

        when {

            (error != null && items.itemCount > 0) || items.itemCount > 0-> {
                error?.let {
                    errorContent((it.error as FenrisFriendsNetworkException))
                }
            }
            error != null -> {
                errorContent((error.error as FenrisFriendsNetworkException))
            }
            items.itemCount < 1 ->{
                emptyContent.invoke()
            }
            else -> { }
        }
    }
}
