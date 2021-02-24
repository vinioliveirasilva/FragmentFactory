package com.example.fragmentfactory.common

import com.example.common.network.BusinessException
import com.example.common.network.DefaultRestResponse
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

private fun handleBusinessFailure(
    error: Throwable,
    onFailure: (DefaultRestResponse) -> Unit
) {
    if (error is BusinessException) {
        onFailure(error.responseVO)
    }
}

fun <T> Observable<T>.defaultSubscription()
= this.apply {
    subscribeOn(Schedulers.io())
    observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.subscribeSafely(
    onSuccess: (T) -> Unit
) = this.apply { subscribe(onSuccess, {}) }

fun <T> Observable<T>.doOnBusinessFailure(
    onFailure: (DefaultRestResponse) -> Unit
): Observable<T> {
    return this.onErrorResumeNext { error: Throwable ->
        handleBusinessFailure(error, onFailure)
        Observable.error(error)
    }
}


private fun handleSystemError(
    error: Throwable,
    onSystemError: (Throwable) -> Unit
) {
    if (error !is BusinessException) {
        onSystemError(error)
    }
}

fun <T> Observable<T>.doOnSystemError(
    onError: (Throwable) -> Unit
): Observable<T> {
    return this.onErrorResumeNext { error: Throwable ->
        handleSystemError(error, onError)
        Observable.error(error)
    }
}