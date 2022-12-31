package com.artemissoftware.core_ui.composables.dialog.models

import androidx.annotation.StringRes

data class FFDialogOptions(
    @StringRes val confirmationTextId: Int,
    val confirmation: () -> Unit = {},
    @StringRes val cancelTextId: Int? = null,
    val cancel: () -> Unit = {},
) {


    fun getOptionsType(): FFDialogButtonType{

        return when{

            (cancelTextId != null) ->{
                FFDialogButtonType.DOUBLE_OPTION
            }
            else ->{
                FFDialogButtonType.SINGLE_OPTION
            }

        }
    }


}
