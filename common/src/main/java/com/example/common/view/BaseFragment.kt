package com.example.common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
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

    fun hideLoading() {
        (activity as? BaseActivity)?.loading?.isVisible = false
    }

    fun showLoading() {
        (activity as? BaseActivity)?.loading?.isVisible = true
    }

    fun onBackPressed() {
        activity?.supportFragmentManager?.run {
            if(fragments.size == LAST_FRAG_SIZE) activity?.finish()
            else beginTransaction().remove(fragments.last()).commit()
        }
    }

    companion object {
        private const val LAST_FRAG_SIZE = 1
    }
}