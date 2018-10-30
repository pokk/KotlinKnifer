@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

inline fun ui(noinline block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(Main, block = block)

inline fun bkg(noinline block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(Default, block = block)

inline fun io(noinline block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(IO, block = block)
