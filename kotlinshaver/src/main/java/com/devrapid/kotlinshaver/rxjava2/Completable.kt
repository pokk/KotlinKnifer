package com.devrapid.kotlinshaver.rxjava2

import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

fun completableObserver() = CompletablePlugin()

fun completableObserver(
    onError: (Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    onSubscribe: (Disposable) -> Unit = {}
) = CompletablePlugin().apply {
    onSubscribe(onSubscribe)
    onComplete(onComplete)
    onError(onError)
}

class CompletablePlugin : CompletableObserver {
    private var _onSubscribe: ((Disposable) -> Unit)? = null
    private var _onComplete: (() -> Unit)? = null
    private var _onError: ((Throwable) -> Unit)? = null

    override fun onSubscribe(d: Disposable) = this._onSubscribe?.invoke(d) ?: Unit
    override fun onComplete() = this._onComplete?.invoke() ?: Unit
    override fun onError(e: Throwable) = this._onError?.invoke(e) ?: Unit

    fun onSubscribe(onSubscribeFun: (Disposable) -> Unit) = apply { _onSubscribe = onSubscribeFun }
    fun onComplete(onCompleteFun: () -> Unit) = apply { _onComplete = onCompleteFun }
    fun onError(onErrorFun: (t: Throwable) -> Unit) = apply { _onError = onErrorFun }

    fun CompletablePlugin.copy(): CompletablePlugin = CompletablePlugin().apply {
        // TODO(jieyi): 8/14/17 Those functions are the same pointer as before. Duplicate the functions!
        _onSubscribe = this@copy._onSubscribe
        _onComplete = this@copy._onComplete
        _onError = this@copy._onError
    }
}
