@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author  jieyi
 * @since   11/8/17
 */

inline fun View.hideSoftKeyboard() = run {
    clearFocus()
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

inline fun Context.toggleSoftKeyboard() =
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

inline fun Context.isHidingSoftKeyBoard() = !isShowingSoftKeyBoard()

inline fun Context.isShowingSoftKeyBoard() =
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).isAcceptingText