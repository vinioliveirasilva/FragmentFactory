package com.example.academia.home.view.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.academia.R
import com.example.common.view.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class AddWorkoutActivity : BaseActivity(R.layout.activity_add_workout) {

    private val presenter: AddWorkoutPresenter by inject { parametersOf(this) }

    override fun activityModules(): Module {
        return module {
            factory {
                AddWorkoutPresenter(
                    view = this@AddWorkoutActivity
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.init()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AddWorkoutActivity::class.java)
    }
}
