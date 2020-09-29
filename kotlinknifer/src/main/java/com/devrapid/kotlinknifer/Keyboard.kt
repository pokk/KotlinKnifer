@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

inline fun View.hideSoftKeyboard() = run {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}

inline fun View.showSoftKeyboard() =
    apply {
        isFocusable = true
        isFocusableInTouchMode = true
        requestFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_FORCED)
    }

fun isShowingSoftKeyboard(rootView: View): Boolean {
    val softKeyboardHeight = 100
    val r = Rect()

    return rootView.run {
        getWindowVisibleDisplayFrame(r)

        val heightDiff = bottom - r.bottom

        heightDiff > softKeyboardHeight * displayMetrics().density
    }
}

inline fun Context.toggleSoftKeyboard() =
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
