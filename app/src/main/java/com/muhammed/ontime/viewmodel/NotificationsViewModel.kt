package com.muhammed.ontime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammed.ontime.datasource.SchedulesRepository
import com.muhammed.ontime.pojo.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val repository: SchedulesRepository) :
    ViewModel() {
    private val _schedulesList = MutableStateFlow<List<Schedule>>(emptyList())
    val schedulesList: StateFlow<List<Schedule>> get() = _schedulesList

    fun fetchSchedules() {
        viewModelScope.launch {
            _schedulesList.value = repository.getAllActiveSchedules()
        }
    }
}