package com.artemissoftware.core_ui.composables.dialog


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogButtonType
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogOptions
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.core_ui.composables.icon.FFCircularIcon
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.TextNewRodinBold
import com.artemissoftware.core_ui.theme.primaryText
import kotlinx.coroutines.MainScope

@Composable
fun FFDialog(ffUiScaffoldState: FFUiScaffoldState) {

    ffUiScaffoldState.dialog.value?.let { dialogType->

        Dialog(
            onDismissRequest = { },
            content = {
                FFDialog(
                    ffUiScaffoldState = ffUiScaffoldState,
                    dialogType = dialogType
                )
            }
        )
    }

}



@Composable
private fun FFDialog(
    ffUiScaffoldState: FFUiScaffoldState,
    dialogType: FFDialogType
){
    val imageContent: @Composable () -> Unit = when{

        dialogType.imageId != null ->{
            {
                Image(
                    painter = painterResource(id = dialogType.imageId),
                    contentDescription = null,
                    colorFilter  = ColorFilter.tint(
                        color = dialogType.iconColor
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .height(70.dp)
                        .fillMaxWidth(),

                    )
            }
        }
        dialogType.icon != null ->{
            {
                Box(modifier = Modifier.fillMaxWidth()) {

                    FFCircularIcon(
                        modifier = Modifier
                            .padding(top = 35.dp)
                            .align(Alignment.Center),
                        icon = dialogType.icon,
                        iconColor = dialogType.iconColor,
                        backgroundAlpha = 0.1F,
                        size = 70.dp,
                        iconPadding = 12.dp
                    )
                }


            }
        }
        else ->{
            {}
        }
    }

    FFDialog(
        ffUiScaffoldState = ffUiScaffoldState,
        dialogType = dialogType,
        imageContent = imageContent
    )

}


@Composable
private fun FFDialog(
    ffUiScaffoldState: FFUiScaffoldState,
    dialogType: FFDialogType,
    imageContent: @Composable () -> Unit
){

    val textColor = when(dialogType){

        is FFDialogType.Error, is FFDialogType.Info ->{
            Color.White
        }

        else -> MaterialTheme.colors.primaryText
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 5.dp, bottom = 10.dp),
        elevation = 8.dp
    ) {
        Column {

            imageContent()

            FFDialogMessage(dialogType = dialogType)

            FFDialogOptions(
                ffUiScaffoldState = ffUiScaffoldState,
                mainColor = dialogType.mainColor,
                textColor = textColor,
                dialogOptions = dialogType.dialogOptions
            )
        }
    }
}


@Composable
private fun FFDialogOptions(
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
private fun FFDialogPreview(){

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
            confirmationTextId = R.string.confirm
        )
    )

    Column {
        FFDialog(FFUiScaffoldState(MainScope()), dialogTypeSuccess)
        FFDialog(FFUiScaffoldState(MainScope()), dialogTypError)
    }
}









