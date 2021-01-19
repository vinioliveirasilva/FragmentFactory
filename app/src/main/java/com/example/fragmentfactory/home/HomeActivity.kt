package com.example.fragmentfactory.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseActivity
import org.koin.core.module.Module
import org.koin.dsl.module

class HomeActivity: BaseActivity(R.layout.activity_home) {
    override fun activityModules(): Module {
        return module {  }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Home"
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}