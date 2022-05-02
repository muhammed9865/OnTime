package com.muhammed.ontime.datasource

import androidx.room.*
import com.muhammed.ontime.pojo.Note

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM NOTES")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    suspend fun getNoteByTitle(title: String): List<Note>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): Note
}