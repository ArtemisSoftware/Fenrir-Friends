package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.TextNewRodin8
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType


@Composable
fun BreedDetail(
    modifier: Modifier = Modifier,
    breed: Breed,
    detailType: BreedDetailType
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        BreedField(
            fieldValue = breed.name,
            filedName = stringResource(R.string.name)
        )

        if(detailType == BreedDetailType.RESUME || detailType == BreedDetailType.FULL_DETAIL) {

            BreedField(
                fieldValue = breed.group,
                filedName = stringResource(R.string.group)
            )

            BreedField(
                fieldValue = breed.origin,
                filedName = stringResource(R.string.origin)
            )
        }

        if(detailType == BreedDetailType.FULL_DETAIL) {

            BreedField(
                fieldValue = breed.temperament,
                filedName = stringResource(R.string.temperament)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BreedDetailPreview() {
    BreedDetail(breed =  Breed.mockBreeds[0], detailType = BreedDetailType.FULL_DETAIL)
}


@Composable
private fun BreedField(
    filedName: String,
    fieldValue: String? = null
) {

    fieldValue?.let {

        if (it.isNotBlank()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                FFText(
                    text = filedName,
                    style = TextNewRodin8
                )

                FFText(
                    text = it
                )

            }
        }
    }


}



@Preview(showBackground = true)
@Composable
private fun BreedFieldPreview() {
    BreedField(filedName =  "filedName", fieldValue = "fieldValue")
}