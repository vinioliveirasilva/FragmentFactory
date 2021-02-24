package com.example.common.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.common.R
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import kotlin.properties.Delegates

abstract class BaseActivity(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {

    var loading: View by Delegates.notNull()

    abstract fun activityModules(): Module

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(activityModules())
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setSupportActionBar(findViewById(R.id.toolbar))

        loading = findViewById(R.id.loading)
    }

    override fun onDestroy() {
        unloadKoinModules(activityModules())
        super.onDestroy()
    }

    fun hideLoading() {
        loading.isVisible = false
    }

    fun showLoading() {
        loading.isVisible = true
    }

    //Passa a responsabilidade do voltar (Hardware) para o fragment
    override fun onBackPressed() {
        (supportFragmentManager.fragments.lastOrNull() as? BaseFragment)?.onBackPressed() ?: super.onBackPressed()
    }

    fun startActivityAndFinish(intent: Intent) {
        startActivity(intent)
        finish()
    }

    //Passar para dentro do fragmentManager
    fun Fragment.add() {
        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,
            this
        ).commit()
    }

    fun Fragment.replace() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            this
        ).commit()
    }
}