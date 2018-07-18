package com.devrapid.kotlinknifer.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devrapid.kotlinknifer.loge
import com.devrapid.kotlinknifer.logw

/**
 * Wrapper the linear layout manager for catching the exceptions and don't make app crash.
 *
 * @author  jieyi
 * @since   2018/01/09
 */
class WrapContentLinearLayoutManager : LinearLayoutManager {
    constructor(context: Context) : super(context)
    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
        super(context, attrs, defStyleAttr, defStyleRes)

    // FIXED(jieyi): 9/23/17 Workaround for fixing the android original recycler view problem.
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        }
        catch (e: Exception) {
            logw(e.message)
            loge(e)
        }
    }
}
