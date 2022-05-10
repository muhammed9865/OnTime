package com.muhammed.ontime.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.ontime.adapter.SchedulesAdapter
import com.muhammed.ontime.databinding.ListItemDayWithSchedulesBinding
import com.muhammed.ontime.pojo.Schedule
import com.muhammed.ontime.pojo.relations.DayWithSchedules

class DayWithSchedulesViewHolder(private val binding: ListItemDayWithSchedulesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dayWithSchedule: DayWithSchedules, onFinish: ((Boolean, Schedule) -> Unit)?) {
        val mAdapter = SchedulesAdapter(false)
        binding.daySchedulesRv.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
        if (onFinish != null) {
            mAdapter.setOnScheduleFinishListener(onFinish)
        }

        mAdapter.submitList(dayWithSchedule.schedules)

        binding.dayNumber.text = dayWithSchedule.day.day.toString()
    }
}