package com.muhammed.ontime.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.muhammed.ontime.Utils
import com.muhammed.ontime.databinding.ListItemNotificationScheduleBinding
import com.muhammed.ontime.pojo.Schedule

class NotificationsScheduledViewHolder(private val binding: ListItemNotificationScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(schedule: Schedule) {
        with(binding) {
            scheduleNotificationTitle.text = schedule.title
            scheduleNotificationDate.text = Utils.milliToDateAsString(schedule.startFrom)
        }
    }
}