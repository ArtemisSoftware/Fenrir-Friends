package com.artemissoftware.fenrirfriends.composables.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.data.errors.FenrisFriendsNetworkException

@Composable
fun <T: Any>HandlePagingResult(
    items: LazyPagingItems<T>,
    loading: (Boolean) -> Unit = {},
    errorEvent: (FenrisFriendsNetworkException) -> Unit,
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
        loading.invoke((loadState.append is LoadState.Loading) && items.itemCount < 1)

        when {

            (error != null && items.itemCount > 0) || items.itemCount > 0-> {
                error?.let {
                    errorEvent((it.error as FenrisFriendsNetworkException))
                }
            }
            error != null -> {
                LaunchedEffect(key1 = loadState.append){
                    if(loadState.refresh is LoadState.Error) {
                        errorEvent((error.error as FenrisFriendsNetworkException))
                    }
                }

            }
            else -> {}
        }
    }
}
