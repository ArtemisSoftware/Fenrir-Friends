package com.artemissoftware.core_ui.composables.animations.lottie

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*

@Composable
fun FFLottieLoader(
    @RawRes id: Int,
    modifier: Modifier = Modifier
) {


    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(id)
        )

    LottieAnimation(
        composition = compositionResult.value,
        isPlaying  = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f,
        modifier = modifier
    )

}
