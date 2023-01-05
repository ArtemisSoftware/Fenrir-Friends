package com.artemissoftware.fenrirfriends.screen.breedsearch.composables

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.animations.lottie.FFLottieLoader
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.TextNewRodin14
import com.artemissoftware.fenrirfriends.R


@Composable
fun SearchResultsPlaceHolder(
    @StringRes messageId: Int
) {

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1F else 0f,
        animationSpec = tween(
            durationMillis = 5500
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(alpha = alphaAnim),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            FFLottieLoader(
                id = R.raw.lottie_data_not_found,
                modifier = Modifier.size(200.dp)
            )
            FFText(
                text = stringResource(messageId),
                style = TextNewRodin14
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultsPlaceHolderPreview() {

    SearchResultsPlaceHolder(messageId = R.string.name)
}
