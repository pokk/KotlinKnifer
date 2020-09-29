package com.devrapid.kotlinknifer

import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import com.devrapid.kotlinknifer.DrawableDirectionConst.DRAWABLE_DIRECTION_BOTTOM
import com.devrapid.kotlinknifer.DrawableDirectionConst.DRAWABLE_DIRECTION_END
import com.devrapid.kotlinknifer.DrawableDirectionConst.DRAWABLE_DIRECTION_START
import com.devrapid.kotlinknifer.DrawableDirectionConst.DRAWABLE_DIRECTION_TOP
import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.TYPE
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER

/**
 * @author jieyi
 * @since 09/11/18
 */
object DrawableDirectionConst {
    const val DRAWABLE_DIRECTION_START = 0x1
    const val DRAWABLE_DIRECTION_TOP = 0x2
    const val DRAWABLE_DIRECTION_END = 0x4
    const val DRAWABLE_DIRECTION_BOTTOM = 0x8
}

@Retention(SOURCE)
@Target(TYPE, FIELD, VALUE_PARAMETER)
@IntDef(DRAWABLE_DIRECTION_START,
        DRAWABLE_DIRECTION_TOP,
        DRAWABLE_DIRECTION_END,
        DRAWABLE_DIRECTION_BOTTOM)
annotation class DrawableDirection

fun TextView.addDrawable(
    @DrawableRes drawableId: Int,
    color: Int,
    @DrawableDirection direct: Int,
    ratioWidth: Float = 1f,
    ratioHeight: Float = 1f,
) {
    // Modify the icon size.
    context.scaledDrawable(drawableId, ratioWidth, ratioHeight)
        // Modify the icon bound.
        .apply {
            setBounds(0, 0, (minimumWidth * ratioWidth).toInt(), (minimumHeight * ratioHeight).toInt())
            if (color != 0) changeColor(color)
        }
        .let {
            when (direct) {
                DRAWABLE_DIRECTION_START -> setCompoundDrawables(it, null, null, null)
                DRAWABLE_DIRECTION_TOP -> setCompoundDrawables(null, it, null, null)
                DRAWABLE_DIRECTION_END -> setCompoundDrawables(null, null, it, null)
                DRAWABLE_DIRECTION_BOTTOM -> setCompoundDrawables(null, null, null, it)
            }
        }
}

fun TextView.addDrawable(
    @DrawableRes drawableId: Int,
    @DrawableDirection direct: Int,
    ratioWidth: Float = 1f,
    ratioHeight: Float = 1f,
) = addDrawable(drawableId, 0, direct, ratioWidth, ratioHeight)

fun TextView.addDrawableWithIntrinsicBounds(
    @DrawableRes drawableId: Int,
    color: Int,
    @DrawableDirection direct: Int,
    ratioWidth: Float = 1f,
    ratioHeight: Float = 1f,
) {
    // Modify the icon size.
    context.scaledDrawable(drawableId, ratioWidth, ratioHeight)
        // Modify the icon bound.
        .apply {
            setBounds(0, 0, (minimumWidth * ratioWidth).toInt(), (minimumHeight * ratioHeight).toInt())
            if (color != 0) changeColor(color)
        }
        .let {
            when (direct) {
                DRAWABLE_DIRECTION_START -> setCompoundDrawablesWithIntrinsicBounds(it, null, null, null)
                DRAWABLE_DIRECTION_TOP -> setCompoundDrawablesWithIntrinsicBounds(null, it, null, null)
                DRAWABLE_DIRECTION_END -> setCompoundDrawablesWithIntrinsicBounds(null, null, it, null)
                DRAWABLE_DIRECTION_BOTTOM -> setCompoundDrawablesWithIntrinsicBounds(null, null, null, it)
            }
        }
}

fun TextView.addDrawableWithIntrinsicBounds(
    @DrawableRes drawableId: Int,
    @DrawableDirection direct: Int,
    ratioWidth: Float = 1f,
    ratioHeight: Float = 1f,
) = addDrawableWithIntrinsicBounds(drawableId, 0, direct, ratioWidth, ratioHeight)
