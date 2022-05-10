package com.muhammed.ontime.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.ontime.R
import com.muhammed.ontime.Utils
import com.muhammed.ontime.databinding.ListItemScheduleBinding

import com.muhammed.ontime.pojo.Schedule

class ScheduleViewHolder(private val binding: ListItemScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        schedule: Schedule,
        onFinishStatusChange: ((isFinished: Boolean, schedule: Schedule) -> Unit)?
    ) {
        with(binding) {
            scheduleTitle.text = schedule.title
            scheduleTime.text = Utils.getHourFromMillisAsString(schedule.startFrom)
            schedulePlace.text = schedule.place
            scheduleNote.text = schedule.note
            scheduleIsFinishedCb.isChecked = schedule.isFinished

            _onFinishStatusChange(schedule.isFinished)

            scheduleIsFinishedCb.setOnCheckedChangeListener { _, _ ->
                val isFinished = scheduleIsFinishedCb.isChecked
                schedule.isFinished = isFinished
                _onFinishStatusChange(isFinished)

                if (onFinishStatusChange != null) {
                    onFinishStatusChange(isFinished, schedule)
                }
            }

        }
    }

    private fun _onFinishStatusChange(isFinished: Boolean) {
        if (isFinished) {
            changeThemeToOff()
        } else {
            changeThemeToOn()
        }
    }

    private fun changeThemeToOff() = with(binding) {
        val context = itemView.context

        scheduleCardBg.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.schedule_background_off
            )
        )
        val textColor = R.color.schedule_text_off
        // Changing all textViews color to off
        textView15.setTextColor(ContextCompat.getColor(context, textColor))
        textView17.setTextColor(ContextCompat.getColor(context, textColor))
        textView18.setTextColor(ContextCompat.getColor(context, textColor))
        scheduleNote.setTextColor(ContextCompat.getColor(context, textColor))
        schedulePlace.setTextColor(ContextCompat.getColor(context, textColor))
        scheduleTime.setTextColor(ContextCompat.getColor(context, textColor))
        scheduleTitle.setTextColor(ContextCompat.getColor(context, textColor))

        // Changing BreakLine color to off
        scheduleBreakLine.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.schedule_underline_off
            )
        )

    }

    private fun changeThemeToOn() = with(binding) {
        val context = itemView.context
        scheduleCardBg.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.schedule_background_on
            )
        )

        val textColor = R.color.schedule_text_on
        // Changing all textViews color to on
        textView15.setTextColor(ContextCompat.getColor(context, textColor))
        textView17.setTextColor(ContextCompat.getColor(context, textColor))
        textView18.setTextColor(ContextCompat.getColor(context, textColor))
        scheduleNote.setTextColor(ContextCompat.getColor(context, textColor))
        schedulePlace.setTextColor(ContextCompat.getColor(context, textColor))
        scheduleTime.setTextColor(ContextCompat.getColor(context, textColor))
        scheduleTitle.setTextColor(ContextCompat.getColor(context, textColor))

        // Changing BreakLine color to on
        scheduleBreakLine.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.schedule_underline_on
            )
        )
    }

}