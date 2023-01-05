package com.artemissoftware.fenrirfriends.screen.breedsearch.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.animations.lottie.FFLottieLoader
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.TextNewRodin14
import com.artemissoftware.fenrirfriends.R.*


@Composable
fun SearchStatisticsPlaceHolder(
    isSearching: Boolean,
    numberOfResults: Int
) {
    Box(modifier = Modifier.fillMaxWidth()) {

        if (isSearching) {
            FFLottieLoader(
                id = raw.lottie_search_loading,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterStart)
                    .padding(0.dp)
            )
        }
        FFText(
            style = TextNewRodin14,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(12.dp),
            text = stringResource(string.results, numberOfResults)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchResultsPlaceHolderPreview() {

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        SearchStatisticsPlaceHolder(
            isSearching = true,
            numberOfResults = 11
        )

        SearchStatisticsPlaceHolder(
            isSearching = false,
            numberOfResults = 0
        )
    }

}
