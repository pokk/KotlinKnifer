package com.devrapid.kotlinshaver

operator fun <T> T.plus(tail: List<T>) = mutableListOf(this).apply {
    addAll(tail)
}
