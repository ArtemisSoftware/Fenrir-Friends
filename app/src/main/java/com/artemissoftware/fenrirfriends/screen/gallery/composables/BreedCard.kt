package com.artemissoftware.fenrirfriends.screen.gallery.composables

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.core_ui.composables.card.FFCard
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.TextNewRodin8
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R


@Composable
fun BreedCard(
    breed: Breed,
    showFullDetail: Boolean = false
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(breed.url)
            .size(Size.ORIGINAL)
            .crossfade(500)
            .build()
    )

    FFCard(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color.White)
            .padding(16.dp),
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )

            BreedDetail(breed = breed, showFullDetail = showFullDetail)
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun BreedCardPreview() {
    BreedCard(breed =  Breed.mockBreeds[0], showFullDetail = true)
}

