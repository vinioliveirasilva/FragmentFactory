package com.example.academia.home.view.clock

import android.content.Context
import android.os.*
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.example.academia.R
import com.example.common.view.BaseFragment
import org.koin.core.module.Module
import org.koin.dsl.module


@RequiresApi(Build.VERSION_CODES.O)
class ClockFragment : BaseFragment(R.layout.fragment_clock) {

    private lateinit var imageWarn : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.run {
            imageWarn = findViewById<ImageView>(R.id.iv_warn).setupClickListener()
        }
    }

    private fun ImageView.setupClickListener() = this.apply {
        setOnClickListener { startClock() }
    }

    private fun ImageView.cleanClickListener() = this.apply {
        setOnClickListener {  }
    }

    private fun startClock() {
        context?.run {
            imageWarn.setBackgroundColor(this.getColor(R.color.gray))
        }
        imageWarn.cleanClickListener()

        Handler().postDelayed({
            warn()
        }, 50000)
    }

    private fun warn() {
        context?.run {
            imageWarn.setBackgroundColor(this.getColor(R.color.gray_silver))
        }
        vibrate()
        Handler().postDelayed({
            timeIsUp()
        }, 10000)
    }

    private fun timeIsUp() {
        context?.run {
            imageWarn.setBackgroundColor(this.getColor(R.color.black))
            imageWarn.setupClickListener()
        }
        vibrate()
    }

    private fun vibrate() {
        context?.run {
            getSystemService(this, Vibrator::class.java)?.run {
                vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            }
        }
    }

    override fun fragmentModules(): Module {
        return module {  }
    }
}