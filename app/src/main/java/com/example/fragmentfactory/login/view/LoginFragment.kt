package com.example.fragmentfactory.login.view

import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseFragment
import org.koin.core.module.Module
import org.koin.dsl.module

class LoginFragment: BaseFragment(R.layout.fragment_login) {
    override fun fragmentModules(): Module {
        return module {  }
    }
}