package com.azizutku.here.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun View.setVisible(isVisible: Boolean, invisible: Boolean = false) {
    this.visibility = when {
        isVisible -> View.VISIBLE
        invisible -> View.INVISIBLE
        else -> View.GONE
    }
}

fun Fragment.hideKeyboard() {
    val imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(requireView().windowToken, 0)
}
