package com.artemissoftware.fenrirfriends.composables.breed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.screen.gallery.composables.BreedDetail

@Composable
fun BreedContentRow(
    painter: AsyncImagePainter,
    detailType: BreedDetailType,
    breed: Breed,
) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.weight(1f),
            contentScale = ContentScale.Crop,
        )

        BreedDetail(
            breed = breed,
            detailType = detailType,
            modifier = Modifier.weight(1f).padding(12.dp)
        )
    }
}