package com.muhammed.ontime.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammed.ontime.datasource.SchedulesRepository
import com.muhammed.ontime.pojo.Schedule
import com.muhammed.ontime.pojo.relations.DayWithSchedules
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchedulesViewModel @Inject constructor(private val repository: SchedulesRepository) :
    ViewModel() {
    private val _daysWithSchedules = MutableStateFlow<List<DayWithSchedules>>(emptyList())
    val daysWithSchedules: StateFlow<List<DayWithSchedules>> = _daysWithSchedules

    fun loadDaysWithSchedules() {
        viewModelScope.launch {
            val list = repository.getAllDaysWithSchedules()
            list.forEach {
                it.schedules.filter { schedule ->
                    schedule.finish > System.currentTimeMillis()
                }
            }
            Log.d(TAG, "loadDaysWithSchedules: $list")
            _daysWithSchedules.value = list
        }
    }

    fun updateSchedule(schedule: Schedule) {
        viewModelScope.launch {
            repository.updateSchedule(schedule)
        }
    }

    companion object {
        private const val TAG = "SchedulesViewModel"
    }
}