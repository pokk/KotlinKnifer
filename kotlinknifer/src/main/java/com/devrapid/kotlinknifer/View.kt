@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.graphics.Bitmap
import android.view.View

/**
 * @author  jieyi
 * @since   4/12/17
 */

inline fun View.resizeView(width: Int? = null, height: Int? = null) = this.layoutParams?.apply {
    height?.let {
        this.height = it
    }
    width?.let {
        this.width = it
    }
}

fun Bitmap.resizeImageAsRatio(aspectRatio: Double): Bitmap = this.also {
    val ratio: Double = it.width.toDouble() / it.height.toDouble()

    if (ratio > aspectRatio)
        it.width = (aspectRatio * it.height).toInt()
    else
        it.height = (it.width / aspectRatio).toInt()
}