package com.devrapid.kotlinknifer.recyclerview.itemdecorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecorator(private val space: Int) : RecyclerView.ItemDecoration() {
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
