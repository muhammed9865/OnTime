package com.muhammed.ontime.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammed.ontime.pojo.Schedule

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSchedule(schedule: Schedule)

    @Query("SELECT * FROM Schedule")
    suspend fun fetchAllSchedules(): List<Schedule>

    @Query("SELECT * FROM Schedule WHERE :startDate <= startFrom < :endDate")
    suspend fun fetchSchedulesBetweenTwoDates(startDate: Long, endDate: Long): List<Schedule>

}