package com.muhammed.ontime.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String = "",
    var place: String = "",
    @ColumnInfo(defaultValue = "")
    var note: String = "",
    var startFrom: Long = -1,
    var finish: Long = -1,
    var isFullDay: Boolean = false,
    var isFinished: Boolean = false,
    var reminder: Long = 0,
)