package com.azizutku.here.extensions

import android.view.View

fun View.setVisible(isVisible: Boolean, invisible: Boolean = false) {
    this.visibility = when {
        isVisible -> View.VISIBLE
        invisible -> View.INVISIBLE
        else -> View.GONE
    }
}
