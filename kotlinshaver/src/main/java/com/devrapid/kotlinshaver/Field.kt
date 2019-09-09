@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

import java.lang.reflect.Field
import java.lang.reflect.Method

inline fun Field.accessible() = apply { isAccessible = true }

inline fun Method.accessible() = apply { isAccessible = true }
