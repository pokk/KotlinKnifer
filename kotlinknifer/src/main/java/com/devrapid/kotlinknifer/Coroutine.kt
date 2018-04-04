package com.devrapid.kotlinknifer

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * @author  Jieyi Wu
 * @since   2018/04/04
 */
fun ui(block: suspend CoroutineScope.() -> Unit) = launch(UI, block = block)