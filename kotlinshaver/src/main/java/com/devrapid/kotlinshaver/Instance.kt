@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

/**
 * @author  jieyi
 * @since   2018/01/19
 */
/** This way might cost a little overhead. */
inline fun <reified T : Any> List<*>.toInstance() =
    filterIsInstance<T>().takeIf { it.size == this.size }
