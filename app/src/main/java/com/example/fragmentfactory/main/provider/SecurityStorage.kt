package com.example.fragmentfactory.main.provider

interface SecurityStorage {
    fun getLoggedUser()
    var isUserLogged: Boolean
}

class SecurityStorageImpl : SecurityStorage {

    override fun getLoggedUser() {}
    override var isUserLogged: Boolean = false
}