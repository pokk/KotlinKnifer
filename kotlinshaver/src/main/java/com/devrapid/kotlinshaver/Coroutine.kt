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
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

// --------------------- launch

inline fun gLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    noinline block: suspend CoroutineScope.() -> Unit,
) = GlobalScope.launch(context, start, block)

inline fun ui(noinline block: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(Main.immediate).launch(block = block)

inline fun uiLate(noinline block: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(Main).launch(block = block)

inline fun bkg(noinline block: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(Default).launch(block = block)

inline fun io(noinline block: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(IO).launch(block = block)

// ---------------------- async

inline fun <T> gAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    noinline block: suspend CoroutineScope.() -> T,
) = GlobalScope.async(context, start, block)

inline fun <T> uiAsync(noinline block: suspend CoroutineScope.() -> T) =
    CoroutineScope(Main.immediate).async(block = block)

inline fun <T> uiLateAsync(noinline block: suspend CoroutineScope.() -> T) =
    CoroutineScope(Main).async(block = block)

inline fun <T> bkgAsync(noinline block: suspend CoroutineScope.() -> T) =
    CoroutineScope(Default).async(block = block)

inline fun <T> ioAsync(noinline block: suspend CoroutineScope.() -> T) =
    CoroutineScope(IO).async(block = block)

// ----------------------- with context

suspend inline fun <T> uiSwitch(noinline block: suspend CoroutineScope.() -> T) =
    withContext(Main.immediate, block)

suspend inline fun <T> uiLateSwitch(noinline block: suspend CoroutineScope.() -> T) =
    withContext(Main, block)

suspend inline fun <T> bkgSwitch(noinline block: suspend CoroutineScope.() -> T) =
    withContext(Default, block)

suspend inline fun <T> ioSwitch(noinline block: suspend CoroutineScope.() -> T) =
    withContext(IO, block)
