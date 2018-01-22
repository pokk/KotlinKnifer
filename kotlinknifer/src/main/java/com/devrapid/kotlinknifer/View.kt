@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.support.annotation.ColorRes
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import android.view.WindowManager
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

inline fun Context.nagivationBarHeiht() =
    resources.getIdentifier("navigation_bar_height", "dimen", "android")
        .takeIf { 0 < it }
        ?.let { resources.getDimensionPixelSize(it) } ?: 0

inline fun Context.statusBarHeight() =
    resources.getIdentifier("status_bar_height", "dimen", "android")
        .takeIf { 0 < it }
        ?.let { resources.getDimensionPixelSize(it) } ?: 0

inline fun Activity.statusBarHeight() = Rect()
    .apply { window.decorView.getWindowVisibleDisplayFrame(this) }
    .top

inline fun Activity.changeStatusBarColor(@ColorRes colorRes: Int) {
    if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = getResColor(colorRes)
        }
    else {
        TODO("Don't support the sdk version is less than 21 yet.")
    }
}