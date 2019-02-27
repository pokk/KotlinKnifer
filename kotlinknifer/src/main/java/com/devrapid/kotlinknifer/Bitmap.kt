package com.devrapid.kotlinknifer

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Size
import androidx.annotation.DrawableRes
import java.io.ByteArrayOutputStream

/**
 * @author  jieyi
 * @since   2018/01/12
 */

fun Resources.createBitmap(drawableResId: Int, opts: BitmapFactory.Options? = null, rect: Rect? = null): Bitmap? {
    var bitmap: Bitmap? = null
    openRawResource(drawableResId).use {
        bitmap = BitmapFactory.decodeStream(it, rect, opts)
    }
    return bitmap
}

/**
 * Obtain the width and the height of the resource image without loading into the memory.
 *
 * @param drawableResId drawable resource id.
 * @return [Size] with width and height of the image.
 */
fun Resources.obtainBitmapSize(drawableResId: Int): Size {
    val opts = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    createBitmap(drawableResId, opts)
    return Size(opts.outWidth, opts.outHeight)
}

fun Bitmap.scale(width: Int, height: Int): Bitmap {
    val bmp = Bitmap.createScaledBitmap(this, width, height, false)
    safeRecycle()
    return bmp
}

fun Bitmap.scale(widthRatio: Float, heightRatio: Float): Bitmap {
    val (scaledWidth, scaledHeight) = width * widthRatio to height * heightRatio
    val bmp = Bitmap.createScaledBitmap(this, scaledWidth.toInt(), scaledHeight.toInt(), false)
    safeRecycle()
    return bmp
}

fun Bitmap.scale(ratio: Float) = scale(ratio, ratio)

fun Resources.createCompressedBitmap(
    drawableResId: Int,
    simpleSize: Int = 1,
    bitmapConf: Bitmap.Config? = null
): Bitmap {
    val opts = BitmapFactory.Options().apply {
        inJustDecodeBounds = false
        inSampleSize = simpleSize
        inPreferredConfig = bitmapConf
    }
    return requireNotNull(createBitmap(drawableResId, opts))
}

fun Resources.createScaledBitmap(@DrawableRes drawableResId: Int, width: Int, height: Int) =
    requireNotNull(createBitmap(drawableResId)?.scale(width, height))

fun Resources.createScaledBitmap(@DrawableRes drawableResId: Int, widthRatio: Float, heightRatio: Float) =
    requireNotNull(createBitmap(drawableResId)?.scale(widthRatio, heightRatio))

fun Resources.createScaledBitmap(@DrawableRes drawableResId: Int, ratio: Float) =
    createScaledBitmap(drawableResId, ratio, ratio)

fun Resources.createRegionBitmap(
    drawableResId: Int,
    rect: Rect,
    opts: BitmapFactory.Options = BitmapFactory.Options()
) = openRawResource(drawableResId).use {
    // Create a region decoder.
    val decoder = BitmapRegionDecoder.newInstance(it, false)
    decoder.decodeRegion(rect, opts)
}

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

fun Bitmap.toBytes(format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100) {
    val stream = ByteArrayOutputStream()
    compress(format, quality, stream)
    stream.toByteArray()
}

fun Bitmap.resizeImageAsRatio(aspectRatio: Double): Bitmap = also {
    val ratio: Double = it.width.toDouble() / it.height.toDouble()

    if (ratio > aspectRatio)
        it.width = (aspectRatio * it.height).toInt()
    else
        it.height = (it.width / aspectRatio).toInt()
}

fun Bitmap?.safeRecycle() {
    if (this != null && !isRecycled)
        recycle()
}

object ImageUtils {
    /**
     * @param originWidth the width of the origin bitmap
     * @param originHeight the height of the origin bitmap
     * @param desWidth the max width of the desired bitmap
     * @param desHeight the max height of the desired bitmap
     * @return the optimal sample size to make sure the size of bitmap is not more than the desired.
     */
    fun calculateSampleSize(originWidth: Int, originHeight: Int, desWidth: Int, desHeight: Int): Int {
        var sampleSize = 1
        while ((originWidth / sampleSize) > desWidth && (originHeight / sampleSize) > desHeight) {
            sampleSize *= 2
        }
        return sampleSize
    }
}
