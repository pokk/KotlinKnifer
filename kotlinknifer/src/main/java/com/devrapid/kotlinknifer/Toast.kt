@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.Context
import android.widget.Toast

/**
 * @author  jieyi
 * @since   4/11/17
 */

inline fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()