@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

/** This way might cost a little overhead. */
inline fun <reified T : Any> List<*>.toInstance() = filterIsInstance<T>().takeIf { it.size == this.size }
