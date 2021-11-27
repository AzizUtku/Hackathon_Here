
package com.azizutku.here.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.azizutku.here.R
import com.azizutku.here.databinding.DialogLoadingBinding

class LoadingDialog (context: Context) : Dialog(context) {
    
    private var binding: DialogLoadingBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.dialog_loading,
        null,
        false
    )

    init {
        setContentView(binding.root)
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        setCancelable(false)
    }

}