package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.grid.FFStaggeredVerticalGrid
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType

@Composable
fun GalleryGrid(
    breeds: List<Breed>,
    onItemClick: (Breed) -> Unit,
    detailType: BreedDetailType = BreedDetailType.BULLET
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        FFStaggeredVerticalGrid(
            numColumns = 2,
            modifier = Modifier.padding(4.dp)
        ) {
            breeds.forEach { breed ->

                BreedCard(
                    breed = breed,
                    detailType = detailType,
                    onClick = {
                        onItemClick.invoke(it)
                    }
                )

            }
        }

    }
}
