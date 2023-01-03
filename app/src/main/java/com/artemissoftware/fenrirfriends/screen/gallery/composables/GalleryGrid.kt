package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.core_ui.composables.grid.FFStaggeredVerticalGrid
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.composables.breed.BreedCard
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryGrid(
    breeds: LazyPagingItems<Breed>,
    onItemClick: (Breed) -> Unit,
    detailType: BreedDetailType = BreedDetailType.BULLET
){

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(breeds.itemCount)
        { index ->
            breeds[index]?.let { breed->

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
