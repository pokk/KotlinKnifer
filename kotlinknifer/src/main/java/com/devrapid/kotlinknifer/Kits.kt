@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

/**
 *
 * @author  jieyi
 * @since   7/28/17
 */
inline infix fun (() -> Unit).iff(condition: Any?): Any? {
    return when (condition) {
        is Boolean -> if (condition) this() else null
        is Float, Double, Int, Long -> condition.let { this() }
        is String -> if (condition.isNotBlank()) this() else null
        is Collection<*> -> if (condition.isNotEmpty()) this() else null
        else -> condition?.let { this() }
    }
}

inline fun Any?.isNull() = null == this

inline fun Any?.isNotNull() = null != this
