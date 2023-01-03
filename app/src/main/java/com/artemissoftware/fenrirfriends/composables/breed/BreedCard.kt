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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.core_ui.composables.card.FFCard
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.screen.gallery.composables.BreedDetail


@Composable
fun BreedCard(
    breed: Breed,
    detailType: BreedDetailType = BreedDetailType.BULLET,
    onClick: (Breed) -> Unit,
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(breed.url)
            .size(Size.ORIGINAL)
            .crossfade(500)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_fenrir_placeholder)
            .build()
    )

    FFCard(
        onClick = { onClick.invoke(breed) },
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color.White)
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
}


@Preview(showBackground = true)
@Composable
private fun BreedCardPreview() {
    BreedCard(breed =  Breed.mockBreeds[0], detailType = BreedDetailType.BULLET, onClick = {})
}

