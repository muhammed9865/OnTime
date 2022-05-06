package com.muhammed.ontime.di

import android.content.Context
import androidx.room.Room
import com.muhammed.ontime.datasource.LocalDatabase
import com.muhammed.ontime.datasource.NotesDao
import com.muhammed.ontime.datasource.dao.ScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, "ontime")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(localDatabase: LocalDatabase): NotesDao = localDatabase.notesDao

    @Provides
    @Singleton
    fun provideSchedulesDao(localDatabase: LocalDatabase): ScheduleDao = localDatabase.scheduleDao
}