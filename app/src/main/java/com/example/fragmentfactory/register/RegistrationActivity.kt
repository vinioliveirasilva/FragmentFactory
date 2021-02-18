package com.example.fragmentfactory.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseActivity
import com.example.fragmentfactory.register.view.RegisterUserInformationFragment
import org.koin.core.module.Module
import org.koin.dsl.module

class RegistrationActivity : BaseActivity(R.layout.activity_container) {
    override fun activityModules(): Module {
        return module {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RegisterUserInformationFragment().add()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, RegistrationActivity::class.java)
    }
}