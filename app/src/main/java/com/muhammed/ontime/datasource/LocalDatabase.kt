package com.muhammed.ontime.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhammed.ontime.pojo.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = true
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val notesDao: NotesDao
    abstract val scheduleDao: ScheduleDao
}