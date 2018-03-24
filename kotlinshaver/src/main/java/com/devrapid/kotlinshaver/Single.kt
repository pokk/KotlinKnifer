package com.devrapid.kotlinshaver

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * @author  Jieyi Wu
 * @since   2018/03/25
 */
fun <T> single(): SinglePlugin<T> = SinglePlugin()

fun <T> single(
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onSubscribe: (Disposable) -> Unit = {}
) = SinglePlugin<T>().apply {
    onSubscribe(onSubscribe)
    onSuccess(onSuccess)
    onError(onError)
}

class SinglePlugin<T> : SingleObserver<T> {
    private var _onSubscribe: ((Disposable) -> Unit)? = null
    private var _onSuccess: ((T) -> Unit)? = null
    private var _onError: ((Throwable) -> Unit)? = null

    override fun onSubscribe(d: Disposable) = this._onSubscribe?.invoke(d) ?: Unit
    override fun onSuccess(t: T) = _onSuccess?.invoke(t) ?: Unit
    override fun onError(e: Throwable) = this._onError?.invoke(e) ?: Unit

    fun onSubscribe(onSubscribeFun: (Disposable) -> Unit) = apply { _onSubscribe = onSubscribeFun }
    fun onSuccess(onSuccessFun: (t: T) -> Unit) = apply { _onSuccess = onSuccessFun }
    fun onError(onErrorFun: (t: Throwable) -> Unit) = apply { _onError = onErrorFun }

    fun SinglePlugin<T>.copy(): SinglePlugin<T> = SinglePlugin<T>().apply {
        // TODO(jieyi): 8/14/17 Those functions are the same pointer as before. Duplicate the functions!
        _onSubscribe = this@copy._onSubscribe
        _onSuccess = this@copy._onSuccess
        _onError = this@copy._onError
    }
}
