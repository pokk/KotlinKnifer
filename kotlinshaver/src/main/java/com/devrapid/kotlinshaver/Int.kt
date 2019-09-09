@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

inline fun Int.toBool() = 0 < this

inline fun Boolean.toInt() = if (this) 1 else 0
