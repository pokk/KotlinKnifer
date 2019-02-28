package com.devrapid.kotlinknifer

import android.app.Activity
import android.util.DisplayMetrics

/**
 * Obtain the screen size in pixels.
 *
 * @return a [Pair<Int, Int>] is screen width and height.
 */
fun Activity.displayPixels() = DisplayMetrics().also {
    windowManager.defaultDisplay.getMetrics(it)
}.run { widthPixels to heightPixels }
