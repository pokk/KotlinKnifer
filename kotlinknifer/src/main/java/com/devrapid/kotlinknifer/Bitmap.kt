@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes

/**
 * @author  jieyi
 * @since   2018/01/12
 */
inline fun Context.scaledBitmap(@DrawableRes bitmapId: Int, width: Int, height: Int) =
    bitmapId.toBitmap(this).let { Bitmap.createScaledBitmap(it, width, height, false) }

inline fun Context.scaledBitmap(@DrawableRes bitmapId: Int, widthRatio: Float, heightRatio: Float) =
    bitmapId.toBitmap(this).run {
        val (scaledWidth, scaledHeight) = width * widthRatio to height * heightRatio
        Bitmap.createScaledBitmap(this, scaledWidth.toInt(), scaledHeight.toInt(), false)
    }

inline fun Context.scaledBitmap(@DrawableRes bitmapId: Int, ratio: Float) =
    scaledBitmap(bitmapId, ratio, ratio)

inline fun Int.toBitmap(context: Context) = BitmapFactory.decodeResource(context.resources, this)

inline fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable) return bitmap

    val width = if (bounds.isEmpty) intrinsicWidth else bounds.width()
    val height = if (bounds.isEmpty) intrinsicHeight else bounds.height()

    return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).also {
        Canvas(it).let {
            setBounds(0, 0, it.width, it.height)
            draw(it)
        }
    }
}