package com.muhammed.ontime.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.muhammed.ontime.databinding.ListItemDayWithSchedulesBinding
import com.muhammed.ontime.pojo.Schedule

import com.muhammed.ontime.pojo.relations.DayWithSchedules
import com.muhammed.ontime.viewholder.DayWithSchedulesViewHolder

class DaysWithSchedulesAdapter :
    ListAdapter<DayWithSchedules, DayWithSchedulesViewHolder>(DayWithSchedulesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWithSchedulesViewHolder {
        val binding = ListItemDayWithSchedulesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DayWithSchedulesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayWithSchedulesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onFinish)
        Log.d("DaysWithSchedules", "${item.day.day}\t${item.schedules}")
    }

    private var onFinish: ((Boolean, Schedule) -> Unit)? = null

    fun setOnScheduleFinishListener(onFinish: (isFinished: Boolean, Schedule) -> Unit) {
        this.onFinish = onFinish
    }


    class DayWithSchedulesDiff : DiffUtil.ItemCallback<DayWithSchedules>() {
        override fun areItemsTheSame(
            oldItem: DayWithSchedules,
            newItem: DayWithSchedules
        ): Boolean {
            return oldItem.day.time == newItem.day.time
        }

        override fun areContentsTheSame(
            oldItem: DayWithSchedules,
            newItem: DayWithSchedules
        ): Boolean {
            return oldItem == newItem
        }
    }


}