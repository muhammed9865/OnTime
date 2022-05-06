package com.muhammed.ontime.datasource

import com.muhammed.ontime.datasource.dao.ScheduleDao
import com.muhammed.ontime.pojo.Schedule
import javax.inject.Inject

class SchedulesRepository @Inject constructor(private val schedulesDao: ScheduleDao) {
    suspend fun saveSchedule(schedule: Schedule) {
        if (validateSchedule(schedule)) {
            schedulesDao.saveSchedule(schedule)
        }
    }

    suspend fun getAllSchedules(): List<Schedule> = schedulesDao.fetchAllSchedules()

    private fun validateSchedule(schedule: Schedule): Boolean {
        return schedule.title.isNotEmpty() || schedule.startFrom != -1L || schedule.finish != -1L
    }


}

