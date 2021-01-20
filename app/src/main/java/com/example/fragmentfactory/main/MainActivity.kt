package com.example.fragmentfactory.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseActivity
import com.example.fragmentfactory.home.HomeActivity
import com.example.fragmentfactory.login.LoginActivity
import com.example.fragmentfactory.main.provider.SecurityStorage
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun callHome() {
        startActivityAndFinish(HomeActivity.newIntent(this))
    }

    fun callLogin() {
        startActivityAndFinish(LoginActivity.newIntent(this))
    }
}