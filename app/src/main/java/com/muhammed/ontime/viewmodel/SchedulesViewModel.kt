package com.muhammed.ontime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammed.ontime.datasource.SchedulesRepository
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
            _daysWithSchedules.value = list
        }
    }
}