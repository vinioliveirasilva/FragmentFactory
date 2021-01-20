package com.example.fragmentfactory.main

import com.example.fragmentfactory.main.provider.SecurityStorage

class MainPresenter(
    private val view: MainActivity,
    private val storage: SecurityStorage
) {

    fun initialize() {
        view.run {
            if(storage.isUserLogged) callHome()
            else callLogin()
        }
    }
}