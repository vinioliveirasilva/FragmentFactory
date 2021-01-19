package com.example.fragmentfactory.common

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentfactory.R
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {

    abstract fun activityModules(): Module

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(activityModules())
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onDestroy() {
        unloadKoinModules(activityModules())
        super.onDestroy()
    }

    fun startActivityAndFinish(intent: Intent) {
        startActivity(intent)
        finish()
    }
}