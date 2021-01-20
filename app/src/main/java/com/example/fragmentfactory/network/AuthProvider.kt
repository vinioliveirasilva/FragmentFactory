package com.example.fragmentfactory.network

import rx.Observable
import java.util.concurrent.TimeUnit

interface AuthProvider {
    fun doLogin(user: String, pass: String) : Observable<Boolean>
}

class AuthProviderImpl: AuthProvider {
    private var authSuccess = true
    override fun doLogin(user: String, pass: String) : Observable<Boolean> {
        authSuccess = authSuccess.not()
        return Observable.timer(4000, TimeUnit.MILLISECONDS).map { authSuccess }
    }
}