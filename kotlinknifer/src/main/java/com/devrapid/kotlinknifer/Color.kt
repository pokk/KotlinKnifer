@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")

package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View

/**
 *
 * @author  jieyi
 * @since   5/30/17
 */

/**
 * Add the alpha into the [Color] from resource id.
 *
 * @param resColor opaque RGB integer resColor for ex: R.color.white
 * @param ratio ratio of transparency for ex: 0.5f
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun Context.getResColorWithAlpha(@ColorRes resColor: Int, ratio: Float): Int =
    this.getColorWithAlpha(this.getResColor(resColor), ratio)

/**
 * Add the alpha into the Color.
 *
 * @param color opaque RGB integer color for ex: -11517920
 * @param ratio ratio of transparency for ex: 0.5f
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun Context.getColorWithAlpha(@ColorInt color: Int, ratio: Float): Int {
    val a: Int = Math.round(Color.alpha(color) * ratio)

    return Color.argb(a, Color.red(color), Color.green(color), Color.blue(color))
}

/**
 * Get the [Color] from resource id.
 *
 * @param resColor opaque RGB integer resColor for ex: -11517920
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun Context.getResColor(@ColorRes resColor: Int): Int = ContextCompat.getColor(this, resColor)

/**
 * Get the [Color] from resource id.
 *
 * @param resColor opaque RGB integer resColor for ex: -11517920
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun View.getResColor(@ColorRes resColor: Int): Int = ContextCompat.getColor(this.context, resColor)