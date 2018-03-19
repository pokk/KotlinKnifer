@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * @author  Jieyi Wu
 * @since   2018/03/19
 */
inline fun Context.getDisplayMetrics() = DisplayMetrics().also {
    (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(it)
}