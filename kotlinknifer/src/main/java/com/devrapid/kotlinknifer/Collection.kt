@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import java.util.Stack

/**
 * @author  jieyi
 * @since   2018/01/12
 */
inline fun <E> Stack<E>.safePop() = lastOrNull()?.let { pop() }

inline fun <E> Stack<E>.safePeek() = lastOrNull()?.let { peek() }
