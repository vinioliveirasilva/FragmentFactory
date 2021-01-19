package com.example.fragmentfactory.login.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseFragment
import com.example.fragmentfactory.login.presenter.LoginPresenter
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import kotlin.properties.Delegates

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private var btLogin: Button by Delegates.notNull()
    private var btRegister: Button by Delegates.notNull()

    private val presenter: LoginPresenter by inject { parametersOf(this) }

    override fun fragmentModules(): Module {
        return module {
            factory { LoginPresenter(this@LoginFragment) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.run {
            btLogin = findViewById<Button>(R.id.bt_primary).setupLoginButton()
            btRegister = findViewById<Button>(R.id.bt_secondary).setupRegisterButton()
        }
    }

    private fun Button.setupLoginButton() = apply {
        text = getString(R.string.bt_login_text)
        setOnClickListener {
            presenter.doOnLogin()
        }
    }

    private fun Button.setupRegisterButton() = apply {
        text = getString(R.string.bt_register_text)
        setOnClickListener {
            presenter.doOnRegister()
        }
    }
}