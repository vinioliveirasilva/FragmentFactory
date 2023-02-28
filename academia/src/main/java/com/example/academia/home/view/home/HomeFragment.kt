package com.example.academia.home.view.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.academia.R
import com.example.common.view.BaseActivity
import com.example.common.view.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import kotlin.properties.Delegates

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val presenter: HomePresenter by inject { parametersOf(this) }

    private var rvWorkouts: RecyclerView by Delegates.notNull()
    private var btAddWorkout: Button by Delegates.notNull()

    override fun fragmentModules(): Module {
        return module {
           factory {
                HomePresenter(
                    view = this@HomeFragment,
                    workoutStorage = get()
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.run {
            rvWorkouts = findViewById<RecyclerView>(R.id.rv_workouts).apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            }
            btAddWorkout = findViewById<Button>(R.id.bt_add_workout).apply {
                setOnClickListener {
                    presenter.doOnAddWorkout()
                }
            }
        }
        presenter.init()
    }

    fun setupWorkouts(workouts: MutableList<WorkoutModel>) {
      rvWorkouts.adapter = WorkoutAdapter(workouts).apply {
          setOnLongClickListener { name -> presenter.showDeleteConfirmation(name) }
      }
    }

    fun showDeletionConfirmationPopup(name: String) {
        (activity as? BaseActivity)?.run {
            Toast.makeText(applicationContext, "Tentativa de deleção do treindo - $name", Toast.LENGTH_SHORT).show()
        }
    }

    fun addWorkout(toAdd: WorkoutModel) {
        (rvWorkouts.adapter as? WorkoutAdapter)?.addWorkout(toAdd)
    }

    fun callAddWorkoutView() {
        startActivityForResult(AddWorkoutActivity.newIntent(requireContext()), ADD_WORKOUT_REQ)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.doOnActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val ADD_WORKOUT_REQ = 100
    }
}