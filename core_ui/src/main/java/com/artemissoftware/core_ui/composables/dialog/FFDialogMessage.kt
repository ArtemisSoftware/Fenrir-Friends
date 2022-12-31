package com.artemissoftware.core_ui.composables.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.TextNewRodinBold

@Composable
fun FFDialogMessage(dialogType: FFDialogType){

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        FFText(
            text = dialogType.title,
            style = TextNewRodinBold,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        FFText(
            text = dialogType.description,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
        )
    }

}


@Preview
@Composable
private fun FFDialogMessagePreview(){

    val dialogTypeSuccess = FFDialogType.Success(
        title =  "Get updates",
        description = "Allow permission to send notifications every day of the year",
        icon = Icons.Filled.Build,
        dialogOptions = FFDialogOptions(
            confirmationTextId = R.string.confirm,
            cancelTextId = R.string.cancel
        )
    )

    FFDialogMessage(dialogType = dialogTypeSuccess)
}