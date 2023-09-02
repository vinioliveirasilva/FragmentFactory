package com.example.academia.home.view.home

import android.content.Intent
import com.example.academia.R
import com.example.academia.WorkoutStorage

class HomePresenter(
    private val view: HomeFragment,
    private val workoutStorage: WorkoutStorage
) {

    fun init() {
        view.setupWorkouts(workoutStorage.get())
    }

    fun doOnAddWorkout() {
        view.callAddWorkoutView()
        WorkoutModel("Teste", R.drawable.ic_dashboard_black_24dp).run {
            workoutStorage.save(this)
            view.addWorkout(this)
        }
    }

    fun doOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun showDeleteConfirmation(name: String) {
        view.showDeletionConfirmationPopup(name)
    }
}
