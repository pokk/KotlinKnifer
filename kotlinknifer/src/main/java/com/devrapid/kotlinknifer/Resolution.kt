package com.devrapid.kotlinknifer

import android.content.Context
import android.content.res.Resources
import org.jetbrains.anko.px2dip
import org.jetbrains.anko.px2sp

/**
 * @author  jieyi
 * @since   11/24/17
 */
/** Convert dp to px */
fun Float.dp2px(context: Context) = this * context.resources.displayMetrics.density + 0.5f

/** Convert px to dp */
fun Float.px2dp(context: Context) = context.px2dip(this.toInt())

/** Convert px to sp */
fun Float.px2sp(context: Context) = context.px2sp(this.toInt())

/** Convert sp to px */
fun Float.sp2px(context: Context) = this * context.resources.displayMetrics.scaledDensity + 0.5f

/** Convert dp to px */
fun Int.dp2px(context: Context) = this * context.resources.displayMetrics.density + 0.5f

/** Convert px to dp */
fun Int.px2dp(context: Context) = context.px2dip(this)

/** Convert px to sp */
fun Int.px2sp(context: Context) = context.px2sp(this)

/** Convert sp to px */
fun Int.sp2px(context: Context) = this * context.resources.displayMetrics.scaledDensity + 0.5f

// Convert dimensions without Context
val Int.dp get() = this * Resources.getSystem().displayMetrics.density + 0.5f
val Int.sp get() = this * Resources.getSystem().displayMetrics.scaledDensity + 0.5f
val Float.dp get() = this * Resources.getSystem().displayMetrics.density + 0.5f
val Float.sp get() = this * Resources.getSystem().displayMetrics.scaledDensity + 0.5f
