package com.example.fragmentfactory.login.presenter

import android.util.Log
import com.example.fragmentfactory.common.defaultSubscription
import com.example.fragmentfactory.common.doOnBusinessFailure
import com.example.fragmentfactory.common.doOnSystemError
import com.example.fragmentfactory.common.subscribeSafely
import com.example.fragmentfactory.login.view.LoginFragment
import com.example.fragmentfactory.main.provider.SecurityStorage
import com.example.fragmentfactory.network.AuthProvider

class LoginPresenter(
    private val view: LoginFragment,
    private val authProvider: AuthProvider,
    private val secureStorage: SecurityStorage
) {

    private val tag = this::class.java.canonicalName

    fun doOnLogin(email: String, pass: String) {
        authProvider.doLogin(email, pass)
            .defaultSubscription()
            .doOnSubscribe { view.showLoading() }
            .doOnCompleted { view.hideLoading() }
            .doOnSystemError { Log.e(tag, "Erro na chamada de rede") }
            .doOnBusinessFailure { view.showLoginError() }
            .subscribeSafely {
                secureStorage.isUserLogged = true
                view.callHome()
            }
    }

    fun doOnRegister() {
        view.callRegister()
    }
}
