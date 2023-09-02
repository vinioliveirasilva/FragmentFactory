package com.example.fragmentfactory.network

import rx.Observable
import java.util.concurrent.TimeUnit

interface AuthProvider {
    fun doLogin(user: String, pass: String) : Observable<Boolean>
    fun doLogout() : Observable<Boolean>
}

class AuthProviderImpl: AuthProvider {
    private var authSuccess = true
    override fun doLogin(user: String, pass: String) : Observable<Boolean> {
        authSuccess = authSuccess.not()
        return Observable.timer(DEFAULT_DELAY, TimeUnit.MILLISECONDS).map { authSuccess }
    }

    override fun doLogout() : Observable<Boolean> {
        return Observable.timer(DEFAULT_DELAY, TimeUnit.MILLISECONDS).map { true }
    }

    companion object {
        private const val DEFAULT_DELAY = 2000L
    }
}