package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import androidx.annotation.DrawableRes

fun Context.scaledDrawable(@DrawableRes drawableId: Int, width: Int, height: Int): Drawable {
    val drawable = getDrawable(drawableId)?.apply {
        bounds = Rect(0, 0, width, height)
    } ?: throw NullPointerException()
    return ScaleDrawable(drawable, 0, width.toFloat(), height.toFloat()).drawable ?: throw NullPointerException()
}

fun Context.scaledDrawable(@DrawableRes drawableId: Int, ratioWidth: Float, ratioHeight: Float): Drawable {
    var scaledWidth = 1f
    var scaledHeight = 1f
    val drawable = getDrawable(drawableId)?.apply {
        scaledWidth = intrinsicWidth * ratioWidth
        scaledHeight = intrinsicHeight * ratioHeight
        bounds = Rect(0, 0, scaledWidth.toInt(), scaledHeight.toInt())
    } ?: throw NullPointerException()
    return ScaleDrawable(drawable, 0, scaledWidth, scaledHeight).drawable ?: throw NullPointerException()
}

fun Context.scaledDrawable(@DrawableRes drawableId: Int, ratio: Float) =
    scaledDrawable(drawableId, ratio, ratio)

inline fun Drawable.changeColor(color: Int) = apply {
    colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
}

inline fun Int.toDrawable(context: Context) = context.getDrawable(this)

fun Drawable.toBitmap(): Bitmap {
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
