package com.example.academia.home.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.academia.R
import kotlin.properties.Delegates

class WorkoutAdapter(private var dataSet: MutableList<WorkoutModel>) :
    RecyclerView.Adapter<WorkoutAdapter.ViewHolder>()
{

    private var doOnLongClick: ((String) -> Unit)? = null

    fun setOnLongClickListener(action: (String) -> Unit) {
        doOnLongClick = action
    }

    fun addWorkout(toAdd: WorkoutModel) {
        notifyItemRangeChanged(dataSet.size, 1)
        dataSet.add(toAdd)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView by Delegates.notNull()
        var icon: ImageView by Delegates.notNull()

        init {
            view.run {
                title = findViewById(R.id.tv_workout_title)
                icon = findViewById(R.id.iv_workout_icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_workout, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet[position].run {
            holder.title.text = title
            holder.icon.setImageResource(icon)
            holder.itemView.setOnLongClickListener { true.also { doOnLongClick?.invoke(title) } }
        }
    }

    override fun getItemCount() = dataSet.size
}