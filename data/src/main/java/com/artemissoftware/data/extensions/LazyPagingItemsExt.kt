package com.artemissoftware.data.extensions

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.domain.Resource

fun <T : Any>LazyPagingItems<T>.toResource() : Resource<out Any> {

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    val resource =  when {
        loadState.refresh is LoadState.Loading -> {
            Resource.Loading()
        }
        error != null -> {
            Resource.Error(message = (error.error as FenrisFriendsNetworkException).message ?: "", data = error.error)
        }
        itemCount < 1 ->{
            Resource.Loading()
        }
        else -> {
            Resource.Success(data = this)
        }
    }

    return resource
}
