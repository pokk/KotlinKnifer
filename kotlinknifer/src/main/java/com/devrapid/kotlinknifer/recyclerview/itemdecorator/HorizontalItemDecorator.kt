package com.devrapid.kotlinknifer.recyclerview.itemdecorator

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * The item decorator for horizontal recycler view.
 *
 * @author  jieyi
 * @since   11/11/17
 */
class HorizontalItemDecorator(private val space: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        val childrenCount: Int = parent.childCount

        when {
            0 == position -> outRect.set(space, space, space / 2, space)
            childrenCount - 1 != position -> outRect.set(space / 2, space, space, space)
            else -> outRect.set(space / 2, space, space / 2, space)
        }
    }
}