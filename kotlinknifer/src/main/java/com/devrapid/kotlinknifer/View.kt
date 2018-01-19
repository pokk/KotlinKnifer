@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import com.example.kotlinknifer.R

/**
 * @author  jieyi
 * @since   4/12/17
 */
inline fun View.resizeView(width: Int? = null, height: Int? = null) = layoutParams?.apply {
    height?.let { this.height = it }
    width?.let { this.width = it }
}

inline fun View.visiable() {
    visibility = VISIBLE
}

inline fun View.invisiable() {
    visibility = INVISIBLE
}

inline fun View.gone() {
    visibility = GONE
}

inline fun View.isVisiable() = VISIBLE == visibility

fun View.waitForMeasure(func: (v: View, w: Int, h: Int) -> Unit) {
    if (0 < width && 0 < height) {
        func(this, width, height)

        return
    }
    val listener = object : ViewTreeObserver.OnPreDrawListener {
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

    viewTreeObserver.addOnPreDrawListener(listener)
}

fun Context.alert(message: String, title: String? = null, init: (AlertDialog.Builder.() -> Unit)? = null) =
    AlertDialog.Builder(this).apply {
        this@alert.setTheme(R.style.Base_Theme_AppCompat_Dialog_Alert)
        title?.let { setTitle(title) }
        setMessage(message)
        init?.let { init() }
    }

fun Context.alert(message: Int, title: Int? = null, init: (AlertDialog.Builder.() -> Unit)? = null) =
    AlertDialog.Builder(this).apply {
        this@alert.setTheme(R.style.Base_Theme_AppCompat_Dialog_Alert)
        title?.let { setTitle(title) }
        setMessage(message)
        init?.let { init() }
    }