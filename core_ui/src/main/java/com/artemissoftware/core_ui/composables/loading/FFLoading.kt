package com.artemissoftware.core_ui.composables.loading

import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.animations.lottie.FFLottieLoader
import com.artemissoftware.core_ui.theme.secondaryBackground

@Composable
fun FFLoading(
    isLoading: Boolean,
    @RawRes lottieId: Int = R.raw.lottie_android_icon,
) {

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isLoading
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = false, onClick = {})
                .background(MaterialTheme.colors.secondaryBackground.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            FFLottieLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 40.dp),
                id = lottieId
            )
        }
    }


}
