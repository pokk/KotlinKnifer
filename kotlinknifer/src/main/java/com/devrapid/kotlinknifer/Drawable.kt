package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette

/**
 * @author  jieyi
 * @since   11/20/17
 */
fun Context.scaledDrawable(@DrawableRes drawableId: Int, scaleWidth: Float,
                           scaleHeight: Float): Drawable {
    val drawable = ContextCompat.getDrawable(this, drawableId).apply {
        bounds = Rect(0,
            0,
            (intrinsicWidth * scaleWidth).toInt(),
            (intrinsicHeight * scaleHeight).toInt())
    }
    return ScaleDrawable(drawable, 0, scaleWidth, scaleHeight).drawable
}

fun Bitmap.palette() = Palette.from(this)

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
