package com.artemissoftware.fenrirfriends.screen.breeddetail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.core_ui.composables.scaffold.FFBottomSheetScaffold
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.composables.toolbar.FFToolBar
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.core_ui.composables.window.models.WindowType
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.BreedCard
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.screen.gallery.composables.BreedDetail


@Composable
fun BreedDetailScreen(
    viewModel: BreedDetailViewModel,
    windowSize: WindowSize
) {

    val state = viewModel.state.collectAsState()

    val scaffold = remember { FFUiScaffoldState() }

    BreedDetailScreen(
        state = state.value,
        events = viewModel::onTriggerEvent,
        scaffold = scaffold,
        windowSize = windowSize
    )
}


@Composable
private fun BreedDetailScreen(
    state: BreedDetailState,
    events: ((BreedDetailEvents) -> Unit)? = null,
    scaffold: FFUiScaffoldState,
    windowSize: WindowSize
) {

    when (windowSize.height) {
        WindowType.Medium, WindowType.Expanded -> {
            ColumnContent(state = state, events = events, scaffold = scaffold)
        }
        else -> {

            RowContent(state = state, events = events)
        }
    }

}

@Composable
private fun ColumnContent(
    state: BreedDetailState,
    events: ((BreedDetailEvents) -> Unit)? = null,
    scaffold: FFUiScaffoldState
) {
    FFBottomSheetScaffold(
        ffUiScaffoldState = scaffold,
        isLoading = state.isLoading,
        toolbar = {
            FFToolBar(
                iconColor = Color.White,
                onBackClicked = {
                    events?.invoke(BreedDetailEvents.PopBackStack)
                }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 0.dp, topEnd = 46.dp),
        sheetContent = {

            state.breed?.let {
                BreedDetail(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp, bottom = 16.dp),
                    breed = it,
                    detailType = BreedDetailType.FULL_DETAIL
                )

                scaffold.expandBottomSheet(600)
            }

        },
        content = {
            ImageDisplay(state.breed)
        }
    )

}



@Composable
private fun RowContent(
    state: BreedDetailState,
    events: ((BreedDetailEvents) -> Unit)? = null,
) {

    FFScaffold(
        isLoading = state.isLoading,
        toolbar = {
            FFToolBar(
                iconColor = Color.White,
                onBackClicked = {
                    events?.invoke(BreedDetailEvents.PopBackStack)
                }
            )
        },
        content =  {

            state.breed?.let {
                BreedCard(
                    breed = it,
                    detailType = BreedDetailType.FULL_DETAIL,
                    onClick = {},
                    windowSize = WindowSize(WindowType.Compact, WindowType.Compact)
                )
            }

        }
    )

}



@Composable
private fun ImageDisplay(breed: Breed?) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(breed?.url)
            .size(Size.ORIGINAL)
            .error(R.drawable.ic_fenrir_placeholder)
            .placeholder(R.drawable.ic_fenrir_placeholder)
            .build()
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(if (painter.state is AsyncImagePainter.State.Error) Color.LightGray else Color.Black)
    ){

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }

}





@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun BuildPictureDetailScreenPreview() {

//    val state = BreedDetailState(breed = Breed.mockBreeds[0], isLoading = false)
//    BreedDetailScreen(state = state, scaffold = scaffold)
}