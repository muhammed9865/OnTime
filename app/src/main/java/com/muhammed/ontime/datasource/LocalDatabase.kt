package com.muhammed.ontime.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhammed.ontime.datasource.dao.ScheduleDao
import com.muhammed.ontime.pojo.Note
import com.muhammed.ontime.pojo.Schedule

@Database(
    entities = [Note::class, Schedule::class],
    version = 2,
    exportSchema = true,

    )
abstract class LocalDatabase : RoomDatabase() {
    abstract val notesDao: NotesDao
    abstract val scheduleDao: ScheduleDao
}