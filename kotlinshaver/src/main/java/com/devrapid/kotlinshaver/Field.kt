@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

import java.lang.reflect.Field

inline fun Field.accessible() = apply { isAccessible = true }
