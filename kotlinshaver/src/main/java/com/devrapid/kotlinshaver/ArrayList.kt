package com.devrapid.kotlinshaver

fun <E> ArrayList<E>.removeRange(range: IntRange) =
    if (!(range.first in 0 until size && range.last in 0 until size && range.first > range.last)) {
        false
    }
    else {
        subList(range.first, range.last).clear()
        true
    }
