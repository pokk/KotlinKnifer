package com.devrapid.kotlinknifer

import android.app.Activity
import android.util.DisplayMetrics

/**
 * @author  Jieyi Wu
 * @since   2018/03/05
 */
/**
 * Obtain the screen size in pixels.
 *
 * @return a [Pair<Int, Int>] is screen width and height.
 */
fun Activity.displayPixels() = DisplayMetrics().also {
    windowManager.defaultDisplay.getMetrics(it)
}.run { widthPixels to heightPixels }