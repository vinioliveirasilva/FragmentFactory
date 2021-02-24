package com.example.fragmentfactory.home.view

import com.example.fragmentfactory.R
import com.example.common.view.BaseFragment
import org.koin.core.module.Module
import org.koin.dsl.module

class HomeFragment: BaseFragment(R.layout.fragment_home) {
    override fun fragmentModules(): Module {
        return module {  }
    }
}