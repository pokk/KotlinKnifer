@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

import java.text.DecimalFormat

/**
 * @author  jieyi
 * @since   11/8/17
 */
inline fun String.formatToMoneyKarma(): String = DecimalFormat("###,###").format(java.lang.Double.parseDouble(this))