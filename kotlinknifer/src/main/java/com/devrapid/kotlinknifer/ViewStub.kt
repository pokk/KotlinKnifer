package com.devrapid.kotlinknifer

import android.app.Activity
import android.view.View
import android.view.ViewStub
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun Activity.obtainViewStub(@IdRes stub: Int, @IdRes realView: Int) =
    (findViewById(stub) as? View) ?: findViewById(realView)

fun Fragment.obtainViewStub(@IdRes stub: Int, @IdRes realView: Int) =
    (requireNotNull(view).findViewById(stub) as? View) ?: requireNotNull(view).findViewById(realView)

fun Activity.showViewStub(@IdRes stub: Int, @IdRes realView: Int, options: (View.() -> Unit)? = null) {
    (findViewById<ViewStub>(stub)?.inflate() ?: findViewById(realView)).apply {
        visible()
        bringToFront()
        options?.let(this::apply)
    }
}

fun Fragment.showViewStub(@IdRes stub: Int, @IdRes realView: Int, options: (View.() -> Unit)? = null) {
    (requireNotNull(view).findViewById<ViewStub>(stub)?.inflate() ?: requireNotNull(view).findViewById(realView)).apply {
        visible()
        bringToFront()
        options?.let(this::apply)
    }
}
