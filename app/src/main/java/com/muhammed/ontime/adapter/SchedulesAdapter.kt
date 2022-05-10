package com.muhammed.ontime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.ontime.databinding.ListItemNotificationScheduleBinding
import com.muhammed.ontime.databinding.ListItemScheduleBinding
import com.muhammed.ontime.pojo.Schedule
import com.muhammed.ontime.viewholder.NotificationsScheduledViewHolder
import com.muhammed.ontime.viewholder.ScheduleViewHolder

class SchedulesAdapter(private val isForNotification: Boolean) :
    ListAdapter<Schedule, RecyclerView.ViewHolder>(ScheduleDiff()) {
    override fun getItemViewType(position: Int): Int {
        return if (isForNotification) NOTIFICATION_VH else SCHEDULES_VH
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == NOTIFICATION_VH) {
            val binding = ListItemNotificationScheduleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            NotificationsScheduledViewHolder(binding)
        } else {
            val binding =
                ListItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ScheduleViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NotificationsScheduledViewHolder -> holder.bind(getItem(position))
            is ScheduleViewHolder -> holder.bind(getItem(position), onFinish)
        }
    }

    private var onFinish: ((Boolean, Schedule) -> Unit)? = null

    fun setOnScheduleFinishListener(onFinish: (isFinished: Boolean, schedule: Schedule) -> Unit) {
        this.onFinish = onFinish
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