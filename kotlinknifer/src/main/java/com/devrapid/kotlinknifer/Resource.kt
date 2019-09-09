@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.app.Activity
import android.content.Context
import android.content.res.Resources.Theme
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Build
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

//region Context
inline fun Context.getDimen(@DimenRes id: Int) = resources.getDimension(id)

inline fun Context.getDimenPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

inline fun Context.getAnimation(@AnimatorRes @AnimRes id: Int) = resources.getAnimation(id)

inline fun Context.getIdentifier(name: String, defType: String, defPackage: String) =
    resources.getIdentifier(name, defType, defPackage)

inline fun Context.getDrawable(@DrawableRes id: Int, theme: Theme? = null) =
    resources.getDrawable(id, theme)

@RequiresApi(Build.VERSION_CODES.O)
inline fun Context.getFont(@FontRes id: Int) = resources.getFont(id)

inline fun Context.displayMetrics() = resources.displayMetrics

inline fun Context.createBitmap(
    @DrawableRes id: Int, opts: BitmapFactory.Options? = null,
    rect: Rect? = null
) =
    resources.createBitmap(id, opts, rect)

inline fun Context.obtainBitmapSize(@DrawableRes id: Int) = resources.obtainBitmapSize(id)

inline fun Context.createCompressedBitmap(
    @DrawableRes id: Int, simpleSize: Int = 1,
    bitmapConf: Bitmap.Config? = null
) = resources.createCompressedBitmap(id, simpleSize, bitmapConf)

inline fun Context.createScaledBitmap(@DrawableRes id: Int, width: Int, height: Int) =
    resources.createScaledBitmap(id, width, height)

inline fun Context.createScaledBitmap(@DrawableRes id: Int, widthRatio: Float, heightRatio: Float) =
    resources.createScaledBitmap(id, widthRatio, heightRatio)

inline fun Context.createScaledBitmap(@DrawableRes id: Int, ratio: Float) =
    resources.createScaledBitmap(id, ratio)

inline fun Context.createRegionBitmap(
    @DrawableRes id: Int, rect: Rect,
    opts: BitmapFactory.Options = BitmapFactory.Options()
) = resources.createRegionBitmap(id, rect, opts)
//endregion

//region Activity
inline fun Activity.getDimen(@DimenRes id: Int) = resources.getDimension(id)

inline fun Activity.getDimenPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

inline fun Activity.getAnimation(@AnimatorRes @AnimRes id: Int) = resources.getAnimation(id)

inline fun Activity.getIdentifier(name: String, defType: String, defPackage: String) =
    resources.getIdentifier(name, defType, defPackage)

inline fun Activity.getDrawable(@DrawableRes id: Int, theme: Theme? = null) =
    resources.getDrawable(id, theme)

@RequiresApi(Build.VERSION_CODES.O)

inline fun Activity.getFont(@FontRes id: Int) = resources.getFont(id)

inline fun Activity.displayMetrics() = resources.displayMetrics

inline fun Activity.createBitmap(
    @DrawableRes id: Int, opts: BitmapFactory.Options? = null,
    rect: Rect? = null
) =
    resources.createBitmap(id, opts, rect)

inline fun Activity.obtainBitmapSize(@DrawableRes id: Int) = resources.obtainBitmapSize(id)

inline fun Activity.createCompressedBitmap(
    @DrawableRes id: Int, simpleSize: Int = 1,
    bitmapConf: Bitmap.Config? = null
) = resources.createCompressedBitmap(id, simpleSize, bitmapConf)

inline fun Activity.createScaledBitmap(@DrawableRes id: Int, width: Int, height: Int) =
    resources.createScaledBitmap(id, width, height)

inline fun Activity.createScaledBitmap(
    @DrawableRes id: Int, widthRatio: Float,
    heightRatio: Float
) =
    resources.createScaledBitmap(id, widthRatio, heightRatio)

inline fun Activity.createScaledBitmap(@DrawableRes id: Int, ratio: Float) =
    resources.createScaledBitmap(id, ratio)

inline fun Activity.createRegionBitmap(
    @DrawableRes id: Int, rect: Rect,
    opts: BitmapFactory.Options = BitmapFactory.Options()
) = resources.createRegionBitmap(id, rect, opts)
//endregion

//region Fragment
inline fun Fragment.getDimen(@DimenRes id: Int) = resources.getDimension(id)

inline fun Fragment.getDimenPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

inline fun Fragment.getAnimation(@AnimatorRes @AnimRes id: Int) = resources.getAnimation(id)

inline fun Fragment.getIdentifier(name: String, defType: String, defPackage: String) =
    resources.getIdentifier(name, defType, defPackage)

inline fun Fragment.getDrawable(@DrawableRes id: Int, theme: Theme? = null) =
    resources.getDrawable(id, theme)

@RequiresApi(Build.VERSION_CODES.O)
inline fun Fragment.getFont(@FontRes id: Int) = resources.getFont(id)

inline fun Fragment.displayMetrics() = resources.displayMetrics

inline fun Fragment.createBitmap(
    @DrawableRes id: Int, opts: BitmapFactory.Options? = null,
    rect: Rect? = null
) =
    resources.createBitmap(id, opts, rect)

inline fun Fragment.obtainBitmapSize(@DrawableRes id: Int) = resources.obtainBitmapSize(id)

inline fun Fragment.createCompressedBitmap(
    @DrawableRes id: Int, simpleSize: Int = 1,
    bitmapConf: Bitmap.Config? = null
) = resources.createCompressedBitmap(id, simpleSize, bitmapConf)

inline fun Fragment.createScaledBitmap(@DrawableRes id: Int, width: Int, height: Int) =
    resources.createScaledBitmap(id, width, height)

inline fun Fragment.createScaledBitmap(
    @DrawableRes id: Int, widthRatio: Float,
    heightRatio: Float
) =
    resources.createScaledBitmap(id, widthRatio, heightRatio)

inline fun Fragment.createScaledBitmap(@DrawableRes id: Int, ratio: Float) =
    resources.createScaledBitmap(id, ratio)

inline fun Fragment.createRegionBitmap(
    @DrawableRes id: Int, rect: Rect,
    opts: BitmapFactory.Options = BitmapFactory.Options()
) = resources.createRegionBitmap(id, rect, opts)
//endregion

//region View
inline fun View.getDimen(@DimenRes id: Int) = resources.getDimension(id)

inline fun View.getDimenPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

inline fun View.getAnimation(@AnimatorRes @AnimRes id: Int) = resources.getAnimation(id)

inline fun View.getIdentifier(name: String, defType: String, defPackage: String) =
    resources.getIdentifier(name, defType, defPackage)

inline fun View.getDrawable(@DrawableRes id: Int, theme: Theme? = null) =
    resources.getDrawable(id, theme)

@RequiresApi(Build.VERSION_CODES.O)
inline fun View.getFont(@FontRes id: Int) = resources.getFont(id)

inline fun View.displayMetrics() = resources.displayMetrics

inline fun View.createBitmap(
    @DrawableRes id: Int, opts: BitmapFactory.Options? = null,
    rect: Rect? = null
) =
    resources.createBitmap(id, opts, rect)

inline fun View.obtainBitmapSize(@DrawableRes id: Int) = resources.obtainBitmapSize(id)

inline fun View.createCompressedBitmap(
    @DrawableRes id: Int, simpleSize: Int = 1,
    bitmapConf: Bitmap.Config? = null
) =
    resources.createCompressedBitmap(id, simpleSize, bitmapConf)

inline fun View.createScaledBitmap(@DrawableRes id: Int, width: Int, height: Int) =
    resources.createScaledBitmap(id, width, height)

inline fun View.createScaledBitmap(@DrawableRes id: Int, widthRatio: Float, heightRatio: Float) =
    resources.createScaledBitmap(id, widthRatio, heightRatio)

inline fun View.createScaledBitmap(@DrawableRes id: Int, ratio: Float) =
    resources.createScaledBitmap(id, ratio)

inline fun View.createRegionBitmap(
    @DrawableRes id: Int, rect: Rect,
    opts: BitmapFactory.Options = BitmapFactory.Options()
) = resources.createRegionBitmap(id, rect, opts)
//endregion
