package com.devrapid.kotlinknifer.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devrapid.kotlinknifer.loge
import com.devrapid.kotlinknifer.logw

class WrapContentLinearLayoutManager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayoutManager(context, attrs, defStyleAttr, defStyleRes) {
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
