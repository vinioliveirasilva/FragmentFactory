package com.example.fragmentfactory.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseActivity
import com.example.fragmentfactory.home.view.HomeFragment
import com.example.fragmentfactory.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class HomeActivity: BaseActivity(R.layout.activity_container) {

    private val presenter: HomePresenter by inject { parametersOf(this) }

    override fun activityModules(): Module {
        return module {
            factory {
                HomePresenter(
                    view = this@HomeActivity,
                    authProvider = get(),
                    secureStorage = get()
                )
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Home"
        HomeFragment().add()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_logout -> true.also { presenter.doOnLogout() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun callLogin() {
        startActivityAndFinish(LoginActivity.newIntent(this))
    }

    fun showLogoutError() {
        this.run {
            Toast.makeText(applicationContext, "Logout Error.", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}