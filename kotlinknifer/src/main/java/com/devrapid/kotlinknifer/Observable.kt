package com.devrapid.kotlinknifer

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.internal.operators.observable.ObservableCreate

/**
 *
 * @author  jieyi
 * @since   9/8/17
 */
inline fun <T> observable(crossinline body: (ObservableEmitter<T>) -> Unit): Observable<T> = ObservableCreate { body(it) }