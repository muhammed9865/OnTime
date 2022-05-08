package com.muhammed.ontime.pojo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Day(
    @PrimaryKey
    val time: Long = -1,

    ) {
    @Ignore
    var day: Int = -1

    @Ignore
    var month: Int = -1

    @Ignore
    var year: Int = -1

    init {
        setDate()
    }

    private fun setDate() {
        val cal = Calendar.getInstance()
        cal.timeInMillis = time
        day = cal[Calendar.DAY_OF_MONTH]
        month = cal[Calendar.MONTH]
        year = cal[Calendar.YEAR]
    }
}
