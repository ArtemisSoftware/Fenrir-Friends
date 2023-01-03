package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.composables.breed.BreedCard
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType

@Composable
fun GalleryList(
    breeds: List<Breed>,
    onItemClick: (Breed) -> Unit,
    detailType: BreedDetailType = BreedDetailType.BULLET
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(breeds) { breed->

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

@Composable
fun GalleryList(
    breeds: LazyPagingItems<Breed>,
    onItemClick: (Breed) -> Unit,
    detailType: BreedDetailType = BreedDetailType.BULLET
){
    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(
            items = breeds,
            key = { breed -> breed.id }
        ) { breed ->
            breed?.let { breedDetail->
                BreedCard(
                    breed = breedDetail,
                    detailType = detailType,
                    onClick = {
                        onItemClick.invoke(it)
                    }
                )
            }
        }

    }
}
