package com.example.academia

import com.example.academia.home.view.home.WorkoutModel

interface WorkoutStorage {
    fun get() : MutableList<WorkoutModel>
    fun save(toSave: WorkoutModel)
}

class WorkoutStorageImpl : WorkoutStorage {

    private val workouts = mutableListOf(
        WorkoutModel("Peito", R.drawable.ic_clock),
        WorkoutModel("Braço", R.drawable.ic_home_black_24dp),
        WorkoutModel("Perna", R.drawable.ic_notifications_black_24dp)
    )

    override fun get() = workouts

    override fun save(toSave: WorkoutModel) {
        workouts.add(toSave)
    }
}