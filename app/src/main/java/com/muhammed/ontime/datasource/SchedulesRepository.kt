package com.muhammed.ontime.datasource

import com.muhammed.ontime.datasource.dao.ScheduleDao
import com.muhammed.ontime.pojo.Day
import com.muhammed.ontime.pojo.Schedule
import com.muhammed.ontime.pojo.relations.DayWithSchedules
import java.util.*
import javax.inject.Inject

class SchedulesRepository @Inject constructor(private val schedulesDao: ScheduleDao) {
    suspend fun saveSchedule(schedule: Schedule) {
        if (validateSchedule(schedule)) {
            val cal = Calendar.getInstance()
            cal.timeInMillis = schedule.startFrom
            cal.apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            schedule.dayTime = cal.timeInMillis
            val dayObject = Day(time = cal.timeInMillis)

            schedulesDao.saveScheduleWithDay(schedule, dayObject)
        }
    }

    suspend fun getAllDaysWithSchedules(): List<DayWithSchedules> =
        schedulesDao.fetchDaysWithSchedules()

    suspend fun getAllActiveSchedules(): List<Schedule> = schedulesDao.fetchActiveSchedules()

    private fun validateSchedule(schedule: Schedule): Boolean {
        return schedule.title.isNotEmpty() || schedule.startFrom != -1L || schedule.finish != -1L
    }


}

