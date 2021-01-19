package com.example.fragmentfactory.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseFragment(
    @LayoutRes val layoutId: Int
) : Fragment() {

    abstract fun fragmentModules(): Module

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(fragmentModules())
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        unloadKoinModules(fragmentModules())
        super.onDestroy()
    }
}