package com.devrapid.kotlinshaver

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

inline fun String.formatToMoneyKarma() = DecimalFormat("###,###").format(java.lang.Double.parseDouble(this))

inline fun Long.toMoneyFormat(locale: Locale?) =
    (locale?.let(NumberFormat::getInstance) ?: NumberFormat.getInstance()).format(this)

inline fun Double.toMoneyFormat(locale: Locale?) =
    (locale?.let(NumberFormat::getInstance) ?: NumberFormat.getInstance()).format(this)

inline fun Long.toMoneyFormatWithCurrency(locale: Locale?) =
    (locale?.let(NumberFormat::getCurrencyInstance) ?: NumberFormat.getCurrencyInstance()).format(this)

inline fun Double.toMoneyFormatWithCurrency(locale: Locale?) =
    (locale?.let(NumberFormat::getCurrencyInstance) ?: NumberFormat.getCurrencyInstance()).format(this)

fun String.trimMarginAndNewLine() = trimMargin().replace("\n", "")
