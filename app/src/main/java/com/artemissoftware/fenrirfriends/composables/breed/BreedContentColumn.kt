package com.artemissoftware.fenrirfriends.composables.breed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.artemissoftware.core_ui.composables.card.FFCard
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.screen.gallery.composables.BreedDetail

@Composable
fun BreedContentColumn(
    painter: AsyncImagePainter,
    detailType: BreedDetailType,
    breed: Breed,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        BreedDetail(
            breed = breed,
            detailType = detailType,
            modifier = Modifier.padding(12.dp)
        )
    }

}