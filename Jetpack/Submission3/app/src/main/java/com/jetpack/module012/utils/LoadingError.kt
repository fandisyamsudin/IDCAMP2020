package com.jetpack.module012.utils

import cn.pedant.SweetAlert.SweetAlertDialog
import com.jetpack.module012.R

fun showLoadingError(dialog: SweetAlertDialog, method: (() -> Unit)?) {
    dialog.apply {
        changeAlertType(SweetAlertDialog.WARNING_TYPE)
        titleText = dialog.context.getString(R.string.time_out)
        contentText = dialog.context.getString(R.string.time_out_message)
        setConfirmClickListener {
            method?.let {
                method.invoke()
            }
            dismiss()
        }
        if (!this.isShowing) {
            show()
        }
    }
}