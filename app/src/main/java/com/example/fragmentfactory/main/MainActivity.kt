package com.example.fragmentfactory.main

import android.os.Bundle
import com.example.fragmentfactory.R
import com.example.common.view.BaseActivity
import com.example.fragmentfactory.home.HomeActivity
import com.example.fragmentfactory.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class MainActivity: BaseActivity(R.layout.activity_container) {

    private val presenter: MainPresenter by inject { parametersOf(this) }

    override fun activityModules(): Module {
        return module {
            factory { (view: MainActivity) ->
                MainPresenter(
                    view = view,
                    storage = get()
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initialize()
    }
    
    fun callHome() {
        startActivityAndFinish(HomeActivity.newIntent(this))
    }

    fun callLogin() {
        startActivityAndFinish(LoginActivity.newIntent(this))
    }
}