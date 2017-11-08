@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

/**
 * @author  jieyi
 * @since   11/8/17
 */
inline fun threadName(): String = Thread.currentThread().name