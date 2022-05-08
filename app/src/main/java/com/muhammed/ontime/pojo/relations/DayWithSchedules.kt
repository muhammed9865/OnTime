package com.muhammed.ontime.pojo.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.muhammed.ontime.pojo.Day
import com.muhammed.ontime.pojo.Schedule

data class DayWithSchedules(
    @Embedded
    val day: Day,
    @Relation(
        parentColumn = "time",
        entityColumn = "dayTime"
    )
    val schedules: List<Schedule>
) {

}
