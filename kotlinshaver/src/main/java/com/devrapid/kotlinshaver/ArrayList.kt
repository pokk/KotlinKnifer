package com.devrapid.kotlinshaver

/**
 * @author  jieyi
 * @since   11/8/17
 */
fun <E> ArrayList<E>.removeRange(from: Int, to: Int): Boolean =
    if (!(from in 0 until size && to in 0 until size && from > to)) {
        false
    }
    else {
        subList(from, to).clear()
        true
    }
