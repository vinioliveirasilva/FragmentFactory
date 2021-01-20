package com.example.fragmentfactory.login.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseActivity
import com.example.fragmentfactory.common.BaseFragment
import com.example.fragmentfactory.home.HomeActivity
import com.example.fragmentfactory.login.presenter.LoginPresenter
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import kotlin.properties.Delegates

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private var btLogin: Button by Delegates.notNull()
    private var btRegister: Button by Delegates.notNull()
    private var etEmail: EditText by Delegates.notNull()
    private var etPass: EditText by Delegates.notNull()
    private var loading: View by Delegates.notNull()

    private val presenter: LoginPresenter by inject { parametersOf(this) }

    override fun fragmentModules(): Module {
        return module {
            factory {
                LoginPresenter(
                    view = this@LoginFragment,
                    authProvider = get(),
                    secureStorage = get()
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.run {
            btLogin = findViewById<Button>(R.id.bt_primary).setupLoginButton()
            btRegister = findViewById<Button>(R.id.bt_secondary).setupRegisterButton()
            etEmail = findViewById<EditText>(R.id.et_email_input).setupEmailInput()
            etPass = findViewById<EditText>(R.id.et_password_input).setupPasswordInput()
            loading = findViewById(R.id.loading)
        }
    }

    fun hideLoading() {
        loading.visibility = View.GONE
    }

    fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun Button.setupLoginButton() = apply {
        text = getString(R.string.bt_login_text)
        setOnClickListener {
            presenter.doOnLogin(
                etEmail.text.toString(),
                etPass.text.toString()
            )
        }
    }

    private fun Button.setupRegisterButton() = apply {
        text = getString(R.string.bt_register_text)
        setOnClickListener {
            presenter.doOnRegister()
        }
    }

    private fun EditText.setupEmailInput() = apply {
        hint = getString(R.string.login_email_hint)
    }

    private fun EditText.setupPasswordInput() = apply {
        hint = getString(R.string.login_pass_hint)
    }

    fun callHome() {
        (activity as? BaseActivity)?.run {
            startActivityAndFinish(HomeActivity.newIntent(this))
        }
    }

    fun showLoginError() {
        activity?.run {
            Toast.makeText(applicationContext, "Login e/ou senha incorretos.", Toast.LENGTH_LONG).show()
        }
    }
}