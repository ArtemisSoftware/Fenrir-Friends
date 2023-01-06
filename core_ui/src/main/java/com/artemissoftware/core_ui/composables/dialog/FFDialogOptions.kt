package com.artemissoftware.core_ui.composables.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogButtonType
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.TextNewRodinBold
import com.artemissoftware.core_ui.theme.primaryText
import kotlinx.coroutines.MainScope


@Composable
private fun FGDialogOptions(
    ffUiScaffoldState: FFUiScaffoldState,
    mainColor : Color,
    textColor: Color = MaterialTheme.colors.primaryText,
    dialogOptions: FFDialogOptions
){

    val confirmModifier = if(dialogOptions.getOptionsType() == FFDialogButtonType.DOUBLE_OPTION) Modifier else Modifier.fillMaxWidth()

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(mainColor),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        if(dialogOptions.getOptionsType() == FFDialogButtonType.DOUBLE_OPTION){
            TextButton(
                onClick = {
                    dialogOptions.cancel()
                    ffUiScaffoldState.closeDialog()
                }
            ) {

                FFText(
                    text = stringResource(id = dialogOptions.cancelTextId?: R.string.cancel) ,
                    style = TextNewRodinBold,
                    color = textColor.copy(alpha = 0.4F),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }

        TextButton(
            modifier = confirmModifier,
            onClick = {
                dialogOptions.confirmation()
                ffUiScaffoldState.closeDialog()
            }
        ) {
            FFText(
                text = stringResource(id = dialogOptions.confirmationTextId),
                style = TextNewRodinBold,
                color = textColor,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
    }
}

@Preview
@Composable
private fun FFDialogOptionsPreview(){

    val dialogTypeSuccess = FFDialogType.Success(
        title =  R.string.back_online,
        description = "Allow permission to send notifications every day of the year",
        icon = Icons.Filled.Build,
        dialogOptions = FFDialogOptions(
            confirmationTextId = R.string.confirm,
            cancelTextId = R.string.cancel
        )
    )

    val dialogTypError = FFDialogType.Error(
        title =  R.string.back_online,
        description = "Allow permission to send notifications every day of the year",
        imageId = R.drawable.ic_android,
        dialogOptions = FFDialogOptions(
            confirmationTextId = R.string.confirm,
        )
    )

    Column {
        FGDialogOptions(ffUiScaffoldState = FFUiScaffoldState(MainScope()), mainColor = dialogTypeSuccess.mainColor, dialogOptions = dialogTypeSuccess.dialogOptions)
        FGDialogOptions(ffUiScaffoldState = FFUiScaffoldState(MainScope()), mainColor = dialogTypError.mainColor, dialogOptions = dialogTypError.dialogOptions)
    }
}

