package com.sashakhyzhun.androidbarbershopmanagementprototype.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.annotation.LayoutRes
import android.text.InputType
import android.view.Window
import android.widget.EditText
import com.sashakhyzhun.androidbarbershopmanagementprototype.R

/**
 * @author SashaKhyzhun
 * Created on 9/13/18.
 */
object CustomUI {

    /** Creating UI elements */
    fun createAlertDialog(
            ctx: Context,
            /**
             *
             */
            title: Int,
            message: Int,
            buttonPos: Int,
            buttonNeg: Int,
            editText: EditText?,
            action: () -> Unit
    ): AlertDialog {
        return AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)
                .setView(editText)
                .setNegativeButton(buttonNeg) { dialog, _ -> dialog.cancel() }
                .setPositiveButton(buttonPos) { dialog, _ -> action(); dialog.cancel() }
                .show()
    }

    fun createDialog(
            ctx: Context,
            @LayoutRes contentView: Int
    ): Dialog {
        val dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(contentView)

        return dialog
    }

    fun createEditText(
            ctx: Context,
            text: String?,
            hint: Int
    ): EditText {
        val input = EditText(ctx)
        input.setTextColor(Color.BLACK)
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setText(text ?: ctx.getString(R.string.edit_text_empty_text))
        input.setHint(hint)
        //input.setHintTextColor(ctx.resources.getColor(R.color.secondary_text))
        return input
    }

    fun generateIntent(
            action: String,
            type: String?,
            uri: Uri?
            /*, vararg flags: Any*/
    ): Intent {
        return Intent(action, uri)
                /**
                 *
                 */
                .setType(type)
                /**
                 *
                 */
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                /**
                 *
                 */
                .addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                /**
                 *
                 */
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)

    }


}