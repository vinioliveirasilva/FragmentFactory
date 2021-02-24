package com.example.fragmentfactory.home

import android.util.Log
import com.example.fragmentfactory.main.provider.SecurityStorage
import com.example.fragmentfactory.network.AuthProvider
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomePresenter(
    private val view: HomeActivity,
    private val authProvider: AuthProvider,
    private val secureStorage: SecurityStorage
) {
    private val tag = this::class.java.canonicalName

    fun doOnLogout() {
        authProvider.doLogout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoading() }
            .doOnCompleted { view.hideLoading() }
            .doOnError { Log.e(tag, "Erro na chamada de rede") }
            .subscribe {
                if(it) {
                    secureStorage.isUserLogged = false
                    view.callLogin()
                } else {
                    view.showLogoutError()
                }
            }
    }
}