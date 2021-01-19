package com.example.fragmentfactory.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseActivity
import org.koin.core.module.Module
import org.koin.dsl.module

class LoginActivity: BaseActivity(R.layout.activity_login) {
    override fun activityModules(): Module {
        return module {  }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Login"
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}