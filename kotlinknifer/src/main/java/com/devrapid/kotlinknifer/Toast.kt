package com.devrapid.kotlinknifer

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast

fun Context.buildCustomToast(customLayoutId: Int, tvId: Int, msg: String, durationTime: Int = Toast.LENGTH_SHORT) =
    Toast(this).apply {
        setGravity(Gravity.BOTTOM, 0, (16 * resources.displayMetrics.density).toInt())
        duration = durationTime
        view = LayoutInflater.from(requireNotNull(view).context).inflate(customLayoutId, null).apply {
            findViewById<TextView>(tvId).apply { text = msg }
        }
    }
