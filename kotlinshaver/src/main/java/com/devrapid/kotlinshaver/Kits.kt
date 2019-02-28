@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

inline infix fun (() -> Unit).iff(condition: Any?): Any? {
    return when (condition) {
        is Boolean -> if (condition) this() else null
        is Float, Double, Int, Long -> this()
        is String -> if (condition.isNotBlank()) this() else null
        is Collection<*> -> if (condition.isNotEmpty()) this() else null
        else -> condition?.let { this() }
    }
}

inline fun Any?.isNull() = null == this

inline fun Any?.isNotNull() = null != this

@ExperimentalContracts
inline fun Any?.isNullExp(): Boolean {
    contract {
        returns(false) implies (this@isNullExp == null)
    }
    return null == this
}

@ExperimentalContracts
inline fun Any?.isNotNullExp(): Boolean {
    contract {
        returns(true) implies (this@isNotNullExp != null)
    }
    return null != this
}
