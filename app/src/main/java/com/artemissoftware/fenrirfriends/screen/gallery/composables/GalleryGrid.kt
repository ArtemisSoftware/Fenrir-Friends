package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.grid.FFStaggeredVerticalGrid
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryEvents
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryState

@Composable
fun GalleryGrid(
    state: GalleryState,
    events: ((GalleryEvents) -> Unit)? = null
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        FFStaggeredVerticalGrid(
            numColumns = 2,
            modifier = Modifier.padding(4.dp)
        ) {
            state.breeds.forEach { breed ->

                BreedCard(
                    breed = breed,
                    onClick = {
                        events?.invoke(GalleryEvents.GoToBreedDetail(breed))
                    }
                )

            }
        }

    }
}
