@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import com.example.kotlinknifer.R

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

inline fun View.visiable() {
    this.visibility = VISIBLE
}

inline fun View.gone() {
    this.visibility = GONE
}

inline fun View.isVisiable() = VISIBLE == this.visibility

fun View.waitForMeasure(func: (v: View, w: Int, h: Int) -> Unit) {
    if (0 < this.width && 0 < this.height) {
        func(this, this.width, this.height)

        return
    }

    val listener = object: ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            val observer = this@waitForMeasure.viewTreeObserver
            if (observer.isAlive) {
                observer.removeOnPreDrawListener(this)
            }

            func(this@waitForMeasure, this@waitForMeasure.width, this@waitForMeasure.height)

            observer.removeOnPreDrawListener(this)

            return true
        }
    }

    this.viewTreeObserver.addOnPreDrawListener(listener)
}

fun Context.alert(message: String, title: String? = null, init: (AlertDialog.Builder.() -> Unit)? = null) =
    AlertDialog.Builder(this).apply {
        this@alert.setTheme(R.style.Base_Theme_AppCompat_Dialog_Alert)
        title?.let { this.setTitle(title) }
        this.setMessage(message)
        init?.let { init() }
    }

fun Context.alert(message: Int, title: Int? = null, init: (AlertDialog.Builder.() -> Unit)? = null) =
    AlertDialog.Builder(this).apply {
        this@alert.setTheme(R.style.Base_Theme_AppCompat_Dialog_Alert)
        title?.let { this.setTitle(title) }
        this.setMessage(message)
        init?.let { init() }
    }

fun Bitmap.resizeImageAsRatio(aspectRatio: Double): Bitmap = this.also {
    val ratio: Double = it.width.toDouble() / it.height.toDouble()

    if (ratio > aspectRatio)
        it.width = (aspectRatio * it.height).toInt()
    else
        it.height = (it.width / aspectRatio).toInt()
}