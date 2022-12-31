package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryEvents
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryState

@Composable
fun GalleryList(
    state: GalleryState,
    events: ((GalleryEvents) -> Unit)? = null
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(state.breeds) { breed->

            BreedCard(
                breed = breed,
                onClick = {
                    events?.invoke(GalleryEvents.GoToBreedDetail(breed))
                }
            )
        }
    }
}
