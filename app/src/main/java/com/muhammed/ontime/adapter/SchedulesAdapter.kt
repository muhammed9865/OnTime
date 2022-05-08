package com.muhammed.ontime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.ontime.databinding.ListItemNotificationScheduleBinding
import com.muhammed.ontime.pojo.Schedule
import com.muhammed.ontime.viewholder.NotificationsScheduledViewHolder

class SchedulesAdapter(private val isForNotification: Boolean) :
    ListAdapter<Schedule, RecyclerView.ViewHolder>(ScheduleDiff()) {
    override fun getItemViewType(position: Int): Int {
        return if (isForNotification) NOTIFICATION_VH else SCHEDULES_VH
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ListItemNotificationScheduleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotificationsScheduledViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NotificationsScheduledViewHolder -> holder.bind(getItem(position))
        }
    }

    companion object {
        private const val NOTIFICATION_VH = 0
        private const val SCHEDULES_VH = 1
    }

    class ScheduleDiff : DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }
    }
}