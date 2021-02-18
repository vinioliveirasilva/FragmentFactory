package com.example.fragmentfactory.register.view

import com.example.fragmentfactory.R
import com.example.fragmentfactory.common.BaseFragment
import org.koin.core.module.Module
import org.koin.dsl.module

class RegisterUserInformationFragment: BaseFragment(R.layout.fragment_register_user_info) {
    override fun fragmentModules(): Module {
        return module {}
    }
}