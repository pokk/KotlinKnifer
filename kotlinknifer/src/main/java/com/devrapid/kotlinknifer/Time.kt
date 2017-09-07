package com.devrapid.kotlinknifer

/**
 * Time extension tool.
 *
 * @author  jieyi
 * @since   9/8/17
 */
fun Int.format(digits: Int) = String.format("%0${digits}d", this)

inline fun Int.toTimeString(): String = "${(this / 60).format(2)}:${(this % 60).format(2)}"
