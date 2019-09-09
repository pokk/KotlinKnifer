@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlin.math.roundToInt

/**
 * Add the alpha into the [Color] from resource id.
 *
 * @param resColor opaque RGB integer resColor for ex: R.color.white
 * @param ratio ratio of transparency for ex: 0.5f
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun Context.getColorOfAlpha(@ColorRes resColor: Int, ratio: Float) =
    ContextCompat.getColor(this, resColor).ofAlpha(ratio)

@ColorInt
inline fun Fragment.getColorOfAlpha(@ColorRes resColor: Int, ratio: Float) = getColor(resColor).ofAlpha(ratio)

@ColorInt
inline fun View.getColorOfAlpha(@ColorRes resColor: Int, ratio: Float) = getColor(resColor).ofAlpha(ratio)

/**
 * Add the alpha into the Color.
 *
 * @param ratio ratio of transparency for ex: 0.5f
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun Int.ofAlpha(ratio: Float): Int {
    val alpha = (Color.alpha(this) * ratio).roundToInt()

    return Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}

/**
 * Get the [Color] from resource id.
 *
 * @param resColor opaque RGB integer resColor for ex: -11517920
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun Fragment.getColor(@ColorRes resColor: Int) = ContextCompat.getColor(requireContext(), resColor)

/**
 * Get the [Color] from resource id.
 *
 * @param resColor opaque RGB integer resColor for ex: -11517920
 *
 * @return transparent RGB integer resColor
 */
@ColorInt
inline fun View.getColor(@ColorRes resColor: Int) = ContextCompat.getColor(context, resColor)

@ColorInt
inline fun generateColorInt(@ColorInt color: Int) = Color.rgb(Color.red(color), Color.green(color), Color.blue(color))
