@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.annotation.DrawableRes
import android.support.v7.graphics.Palette

/**
 * @author  jieyi
 * @since   11/20/17
 */
fun Context.scaledDrawable(@DrawableRes drawableId: Int, width: Int, height: Int): Drawable {
    val drawable = getDrawable(drawableId).apply {
        bounds = Rect(0, 0, width, height)
    }
    return ScaleDrawable(drawable, 0, width.toFloat(), height.toFloat()).drawable
}

fun Context.scaledDrawable(@DrawableRes drawableId: Int, ratioWidth: Float, ratioHeight: Float): Drawable {
    var scaledWidth = 1f
    var scaledHeight = 1f
    val drawable = getDrawable(drawableId).apply {
        scaledWidth = intrinsicWidth * ratioWidth
        scaledHeight = intrinsicHeight * ratioHeight
        bounds = Rect(0, 0, scaledWidth.toInt(), scaledHeight.toInt())
    }
    return ScaleDrawable(drawable, 0, scaledWidth, scaledHeight).drawable
}

fun Context.scaledDrawable(@DrawableRes drawableId: Int, ratio: Float) =
    scaledDrawable(drawableId, ratio, ratio)

inline fun Int.toDrawable(context: Context) = context.getDrawable(this)

inline fun Bitmap.toDrawable(context: Context) = BitmapDrawable(context.resources, this).bitmap

inline fun Bitmap.palette() = Palette.from(this)

inline fun Bitmap.palette(maxColorCount: Int) =
    Palette.from(this).maximumColorCount(maxColorCount).generate()

fun Context.blurBitmap(image: Bitmap, radius: Float = 25f, scale: Float = 0.4f): Bitmap {
    val width = Math.round(image.width * scale)
    val height = Math.round(image.height * scale)
    // Because of the blurring, we don't have to use original bitmap to blur. It's able to reduce cost.
    val scaledBitmap = Bitmap.createScaledBitmap(image, width, height, false)
    // Create a image for blurring.
    val blurBitmap = Bitmap.createBitmap(scaledBitmap)
    val rs = RenderScript.create(this)
    // RenderScript doesn't use VM to allocate memory, we have to do it by ourselves.
    val tmpIn = Allocation.createFromBitmap(rs, scaledBitmap)
    // The created Allocation is empty actually, copyTo() is necessary to fill the date.
    val tmpOut = Allocation.createFromBitmap(rs, blurBitmap)

    ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)).apply {
        setRadius(radius)
        setInput(tmpIn)
        forEach(tmpOut)
    }

    tmpOut.copyTo(blurBitmap)

    return blurBitmap
}
