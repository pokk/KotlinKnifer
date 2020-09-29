package com.devrapid.kotlinknifer

import android.graphics.PorterDuff.Mode.SRC_IN
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.devrapid.kotlinshaver.accessible

fun setCursorPointerColor(view: EditText, @ColorInt color: Int, @DrawableRes drawable: Int = -1) {
    // Get the editor.
    val editor = TextView::class.java.getDeclaredField("mEditor").accessible().get(view)
    // Get all drawable's selection points.
    val drawables = if (-1 == drawable) {
        listOf("mTextSelectHandleRes", // Selection cursor point.
               "mTextSelectHandleLeftRes", // Selection the highlight left cursor point.
               "mTextSelectHandleRightRes") // Selection the highlight right cursor point.
            .asSequence()
            .map { TextView::class.java.getDeclaredField(it).accessible() } // Get the drawables' field.
            .map { it.getInt(view) } // Get the drawable resource id.
            .map { requireNotNull(ContextCompat.getDrawable(view.context, it)) } // Get the drawable.
            .map { it.setColorFilter(color, SRC_IN); it } // Change the color we set.
            .toList()
    }
    else {
        (0..2).map { requireNotNull(ContextCompat.getDrawable(view.context, drawable)) }
    }
    // Get all handle fields.
    val fields = listOf("mSelectHandleCenter", "mSelectHandleLeft", "mSelectHandleRight")
        .map { editor.javaClass.getDeclaredField(it).accessible() }
    fields.zip(drawables).forEach { (field, drawable) -> field.set(editor, drawable) }

    view.highlightColor = color
}
