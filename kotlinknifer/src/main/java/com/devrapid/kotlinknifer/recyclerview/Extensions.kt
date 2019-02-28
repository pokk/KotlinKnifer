package com.devrapid.kotlinknifer.recyclerview

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class SlowlySmoothScroller(context: Context) : LinearSmoothScroller(context) {
    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics) = .25f
}

fun RecyclerView.LayoutManager.smoothScrollTo(smoothScroller: LinearSmoothScroller, position: Int) =
    startSmoothScroll(smoothScroller.apply { targetPosition = position })
