@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinshaver

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

// --------------------- launch

inline fun gLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    noinline block: suspend CoroutineScope.() -> Unit
) = GlobalScope.launch(context, start, block)

inline fun ui(noinline block: suspend CoroutineScope.() -> Unit) = gLaunch(Main, block = block)

inline fun bkg(noinline block: suspend CoroutineScope.() -> Unit) = gLaunch(Default, block = block)

inline fun io(noinline block: suspend CoroutineScope.() -> Unit) = gLaunch(IO, block = block)

// ---------------------- async

inline fun <T> gAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    noinline block: suspend CoroutineScope.() -> T
) = GlobalScope.async(context, start, block)

inline fun <T> aUI(noinline block: suspend CoroutineScope.() -> T) = gAsync(Main, block = block)

inline fun <T> aBKG(noinline block: suspend CoroutineScope.() -> T) = gAsync(Default, block = block)

inline fun <T> aIO(noinline block: suspend CoroutineScope.() -> T) = gAsync(IO, block = block)