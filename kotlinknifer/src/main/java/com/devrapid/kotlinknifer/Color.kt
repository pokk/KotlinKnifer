package com.devrapid.kotlinknifer

import android.graphics.Color
import android.support.annotation.ColorRes

/**
 *
 * @author  jieyi
 * @since   5/30/17
 */

/**
 * Add the alpha into the Color.
 *
 * @param color opaque RGB integer color for ex: -11517920
 * @param ratio ratio of transparency for ex: 0.5f
 *
 * @return transparent RGB integer color
 */
fun getColorWithAlpha(@ColorRes color: Int, ratio: Float): Int {
    val a: Int = Math.round(Color.alpha(color) * ratio)
    val r: Int = Color.red(color)
    val g: Int = Color.green(color)
    val b: Int = Color.blue(color)

    return Color.argb(a, r, g, b)
}