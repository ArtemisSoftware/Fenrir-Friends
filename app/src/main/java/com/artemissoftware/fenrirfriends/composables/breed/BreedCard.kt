package com.artemissoftware.fenrirfriends.composables.breed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.core_ui.composables.card.FFCard
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.core_ui.composables.window.models.WindowType
import com.artemissoftware.core_ui.composables.window.models.WindowType.Expanded
import com.artemissoftware.core_ui.composables.window.models.WindowType.Medium
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType


@Composable
fun BreedCard(
    modifier : Modifier = Modifier,
    breed: Breed,
    detailType: BreedDetailType = BreedDetailType.BULLET,
    onClick: (Breed) -> Unit,
    windowSize: WindowSize? = null
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(breed.url)
            .size(Size.ORIGINAL)
            .crossfade(800)
            .error(R.drawable.ic_fenrir_placeholder)
            .placeholder(R.drawable.ic_fenrir_placeholder)
            .build()
    )

    FFCard(
        onClick = { onClick.invoke(breed) },
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color.White)
    ) {

        windowSize?.let {
            when (windowSize.height) {
                Medium, Expanded -> {
                    BreedContentColumn(painter = painter, detailType = detailType, breed = breed)
                }
                else -> {
                    BreedContentRow(painter = painter, detailType = detailType, breed = breed)
                }
            }
        } ?: run {
            BreedContentColumn(painter = painter, detailType = detailType, breed = breed)
        }



    }
}


@Preview(showBackground = true)
@Composable
private fun BreedCardPreview() {
    BreedCard(breed =  Breed.mockBreeds[0], detailType = BreedDetailType.BULLET, onClick = {},
        windowSize = WindowSize(Expanded, Expanded)
    )
}

