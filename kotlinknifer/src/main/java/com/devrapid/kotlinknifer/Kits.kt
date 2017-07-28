@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

/**
 *
 * @author  jieyi
 * @since   7/28/17
 */
inline infix fun (() -> Unit).iff(condition: Any?) {
    when (condition) {
        is Boolean -> if (condition) this()
        is Float, Double, Int, Long -> condition.let { this() }
        is String -> if (condition.isNotBlank()) this()
        is Collection<*> -> if (condition.isNotEmpty()) this()
        else -> condition?.let { this() }
    }
}
