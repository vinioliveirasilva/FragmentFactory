package com.example.fragmentfactory.login.presenter

import android.util.Log
import com.example.fragmentfactory.login.view.LoginFragment
import com.example.fragmentfactory.main.provider.SecurityStorage
import com.example.fragmentfactory.network.AuthProvider
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenter(
    private val view: LoginFragment,
    private val authProvider: AuthProvider,
    private val secureStorage: SecurityStorage
) {

    private val tag = this::class.java.canonicalName

    fun doOnLogin(email: String, pass: String) {
        authProvider.doLogin(email, pass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoading() }
            .doOnCompleted { view.hideLoading() }
            .doOnError { Log.e(tag, "Erro na chamada de rede") }
            .subscribe {
                if(it) {
                    secureStorage.isUserLogged = true
                    view.callHome()
                } else {
                    view.showLoginError()
                }
            }
    }

    fun doOnRegister() {

    }
}