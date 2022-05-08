package com.muhammed.ontime.datasource.dao

import androidx.room.*
import com.muhammed.ontime.pojo.Day
import com.muhammed.ontime.pojo.Schedule
import com.muhammed.ontime.pojo.relations.DayWithSchedules

@Dao
abstract class ScheduleDao {


    @Transaction
    open suspend fun saveScheduleWithDay(schedule: Schedule, day: Day) {
        saveDay(day)
        saveSchedule(schedule)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveSchedule(schedule: Schedule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveDay(day: Day)


    @Query("SELECT * FROM Schedule")
    abstract suspend fun fetchAllSchedules(): List<Schedule>

    @Query("SELECT * FROM Schedule WHERE :startDate <= startFrom < :endDate")
    abstract suspend fun fetchSchedulesBetweenTwoDates(
        startDate: Long,
        endDate: Long
    ): List<Schedule>

    @Transaction
    @Query("SELECT * FROM Day")
    abstract suspend fun fetchDaysWithSchedules(): List<DayWithSchedules>

    @Query("SELECT * FROM Schedule WHERE startFrom >= :currentTime")
    abstract suspend fun fetchActiveSchedules(currentTime: Long = System.currentTimeMillis()): List<Schedule>

}