package com.devrapid.kotlinshaver

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val DATE_YYYY_MM_DD = "yyyyMMdd"
const val DATE_YYYY_MM_DD_WITH_SLASH = "yyyy/MM/dd"
const val DATE_YYYY_MM_DD_WITH_DASH = "yyyy-MM-dd"
const val DATE_YYYY_MM_DD_WITH_DOT = "yyyy.MM.dd"

inline fun Int.format(digits: Int) = String.format("%0${digits}d", this)

inline fun Int.toTimeString() = "${(this / 60).format(2)}:${(this % 60).format(2)}"

inline fun String.toDate(pattern: String = DATE_YYYY_MM_DD, locale: Locale = Locale.US) =
    SimpleDateFormat(pattern, locale).parse(this)

inline fun Date.asString(pattern: String = DATE_YYYY_MM_DD_WITH_SLASH, locale: Locale = Locale.US) =
    SimpleDateFormat(pattern, locale).format(this)

val currentTime get() = System.currentTimeMillis()
