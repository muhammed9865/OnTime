package com.muhammed.ontime.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.muhammed.ontime.Constants
import com.muhammed.ontime.R
import com.muhammed.ontime.datasource.SchedulesRepository
import com.muhammed.ontime.pojo.Schedule
import com.muhammed.ontime.service.NotifyScheduleWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ScheduleDetailsViewModel @Inject constructor(
    private val schedulesRepository: SchedulesRepository,
) :
    ViewModel() {
    private val _schedule = MutableStateFlow(Schedule(dayTime = -1))
    val schedule: StateFlow<Schedule> = _schedule

    fun setTitle(title: String?) {
        _schedule.value.title = title ?: ""
    }

    fun setPlace(place: String?) {
        _schedule.value.place = place ?: ""
    }

    fun setNote(note: String?) {
        _schedule.value.note = note ?: ""
    }

    fun setStartFrom(time: Long) {
        _schedule.value.startFrom = time
    }

    fun setFinish(time: Long) {
        _schedule.value.finish = time
    }

    fun toggleFullDay() {
        _schedule.value.isFullDay = !_schedule.value.isFullDay
    }

    fun setReminder(reminder: String, reminders: Array<String>) {
        val minuteInMills = 30000L
        when (reminder) {
            reminders[0] -> _setReminder(0)
            reminders[1] -> _setReminder(5 * minuteInMills)
            reminders[2] -> _setReminder(10 * minuteInMills)
        }
    }

    fun getReminderAsString(resources: Resources): String? {
        val reminders = resources.getStringArray(R.array.reminders)
        val minuteInMills = 30000L
        return when (_schedule.value.reminder) {
            0L -> reminders[0]
            5 * minuteInMills -> reminders[1]
            10 * minuteInMills -> reminders[2]
            else -> null
        }
    }

    fun saveSchedule(workManager: WorkManager) {
        viewModelScope.launch {
            schedulesRepository.saveSchedule(_schedule.value)
            val data = Data.Builder()
                .putString(Constants.SCHEDULE_TITLE, _schedule.value.title)
                .putString(Constants.SCHEDULE_PLACE, _schedule.value.place)
                .putString(Constants.SCHEDULE_NOTE, _schedule.value.note)
                .build()

            val startRequest = OneTimeWorkRequest.Builder(NotifyScheduleWorker::class.java)
                .setInputData(data)
                .setInitialDelay(_calculateNotifyDelay(), TimeUnit.MILLISECONDS)
                .build()

            workManager.enqueue(startRequest)
        }
    }

    private fun _calculateNotifyDelay(): Long =
        (_schedule.value.startFrom.minus(System.currentTimeMillis())).minus(_schedule.value.reminder)

    private fun _setReminder(millis: Long) {
        _schedule.value.reminder = millis
    }
}