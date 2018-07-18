package com.devrapid.kotlinknifer.recyclerview.itemdecorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * The item decorator for vertical type recycler view.
 *
 * @author  jieyi
 * @since   11/11/17
 */
class VerticalItemDecorator(
    private val topBottom: Int,
    private val leftRight: Int = topBottom
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        val childrenCount: Int = parent.childCount

        when {
            0 == position -> outRect.set(leftRight, topBottom, leftRight, topBottom / 2)
            childrenCount - 1 != position -> outRect.set(leftRight, topBottom / 2, leftRight, topBottom)
            else -> outRect.set(leftRight, topBottom / 2, leftRight, topBottom / 2)
        }
    }
}
