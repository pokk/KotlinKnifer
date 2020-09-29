package com.devrapid.kotlinknifer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.ViewAnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlin.math.pow

fun View.registerCircularRevealAnimation(
    revealSettings: RevealAnimationSetting,
    startColor: Int,
    endColor: Int,
) {
    val duration = 1000L
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int,
            ) {
                v.removeOnLayoutChangeListener(this)
                val (cx, cy, width, height) = revealSettings

                // Simply use the diagonal of the view.
                val radius = Math.sqrt((width.toDouble().pow(2) + height.toDouble().pow(2))).toFloat()
                ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, radius).apply {
                    this.duration = duration
                    interpolator = FastOutSlowInInterpolator()
                }.start()
            }
        })
        startColorAnimation(startColor, endColor, duration)
    }
}

fun View.startCircularExitAnimation(
    revealSettings: RevealAnimationSetting,
    startColor: Int,
    endColor: Int,
    listener: () -> Unit,
) {
    val duration = 1000L
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val (cx, cy, width, height) = revealSettings

        // Simply use the diagonal of the view.
        val radius = Math.sqrt((width.toDouble().pow(2) + height.toDouble().pow(2))).toFloat()
        ViewAnimationUtils.createCircularReveal(this, cx, cy, radius, 0f).apply {
            this.duration = duration
            interpolator = FastOutSlowInInterpolator()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    gone()
                    listener()
                }
            })
        }.start()
        startColorAnimation(startColor, endColor, duration)
    }
    else {
        listener()
    }
}

fun View.startColorAnimation(
    startColor: Int,
    endColor: Int,
    duration: Long,
) {
    ValueAnimator().apply {
        this.duration = duration
        setIntValues(startColor, endColor)
        setEvaluator(ArgbEvaluator())
        addUpdateListener { setBackgroundColor(it.animatedValue as Int) }
    }.start()
}

data class RevealAnimationSetting(
    var centerX: Int,
    var centerY: Int,
    var width: Int,
    var height: Int,
) : Parcelable {
    companion object CREATOR : Parcelable.Creator<RevealAnimationSetting> {
        override fun createFromParcel(parcel: Parcel) = RevealAnimationSetting(parcel)

        override fun newArray(size: Int) = arrayOfNulls<RevealAnimationSetting>(size)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(centerX)
        parcel.writeInt(centerY)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int = 0
}
