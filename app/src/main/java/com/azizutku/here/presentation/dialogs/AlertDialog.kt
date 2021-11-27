package com.azizutku.here.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.azizutku.here.R
import com.azizutku.here.databinding.DialogAlertBinding
import javax.inject.Inject

class AlertDialog @Inject constructor(context: Context) : Dialog(context) {
    private var binding: DialogAlertBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.dialog_alert,
        null,
        false
    )

    init {
        setContentView(binding.root)
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setTitle(title: String?): AlertDialog {
        binding.dialogTxtTitle.text = title
        return this
    }

    fun setMessage(message: String?): AlertDialog {
        binding.dialogTxtMessage.text = message
        binding.dialogTxtMessage.visibility = View.VISIBLE
        return this
    }

    fun setHeaderColor(color: Int): AlertDialog {
        binding.dialogFrameHeader.setBackgroundColor(color)
        return this
    }

    fun setHeaderImage(resId: Int): AlertDialog {
        binding.dialogImgHeader.setImageResource(resId)
        return this
    }

    fun setPositiveButton(
        text: CharSequence?,
        onClickListener: View.OnClickListener
    ): AlertDialog {
        binding.dialogTxtPositiveBtn.visibility = View.VISIBLE
        binding.dialogTxtPositiveBtn.text = text
        binding.dialogTxtPositiveBtn.setOnClickListener(onClickListener)
        return this
    }

    fun setNegativeButton(
        text: CharSequence?,
        onClickListener: View.OnClickListener?
    ): AlertDialog {
        binding.dialogTxtNegativeBtn.visibility = View.VISIBLE
        binding.dialogTxtNegativeBtn.text = text
        binding.dialogTxtNegativeBtn.setOnClickListener(onClickListener)
        return this
    }

    fun setPositiveButton(text: CharSequence?): AlertDialog {
        binding.dialogTxtPositiveBtn.visibility = View.VISIBLE
        binding.dialogTxtPositiveBtn.text = text
        binding.dialogTxtPositiveBtn.setOnClickListener { dismiss() }
        return this
    }

    fun setNegativeButton(text: CharSequence?): AlertDialog {
        binding.dialogTxtNegativeBtn.visibility = View.VISIBLE
        binding.dialogTxtNegativeBtn.text = text
        binding.dialogTxtNegativeBtn.setOnClickListener { dismiss() }
        return this
    }

    override fun dismiss() {
        binding.dialogTxtPositiveBtn.text = context.getString(R.string.text_button_okay)
        binding.dialogTxtPositiveBtn.setOnClickListener { dismiss() }
        binding.dialogTxtPositiveBtn.visibility = View.VISIBLE
        binding.dialogTxtNegativeBtn.visibility = View.GONE
        super.dismiss()
    }
}