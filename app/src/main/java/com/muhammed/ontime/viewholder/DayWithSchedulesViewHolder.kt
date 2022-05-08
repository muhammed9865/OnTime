package com.muhammed.ontime.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.ontime.adapter.SchedulesAdapter
import com.muhammed.ontime.databinding.ListItemDayWithSchedulesBinding
import com.muhammed.ontime.pojo.relations.DayWithSchedules

class DayWithSchedulesViewHolder(private val binding: ListItemDayWithSchedulesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dayWithSchedule: DayWithSchedules) {
        val mAdapter = SchedulesAdapter(true)
        binding.daySchedulesRv.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
        mAdapter.submitList(dayWithSchedule.schedules)

        binding.dayNumber.text = dayWithSchedule.day.day.toString()
    }
}