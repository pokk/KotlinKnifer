package com.devrapid.kotlinshaver

fun <E> ArrayList<E>.removeRange(range: IntRange) =
    if (!(range.start in 0 until size && range.last in 0 until size && range.start > range.last)) {
        false
    }
    else {
        subList(range.start, range.last).clear()
        true
    }
