package com.muhammed.ontime.datasource

import com.muhammed.ontime.pojo.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepository @Inject constructor(private val notesDao: NotesDao) {
    // If received note text is empty, it's a sign that user doesn't want the note anymore (if exists).
    // Else, save the note.
    suspend fun saveNote(note: Note) {
        if (note.text.isEmpty()) {
            notesDao.deleteNote(note)
        } else {
            notesDao.saveNote(note)
        }
    }


    suspend fun updateNote(note: Note) = notesDao.updateNote(note)


    suspend fun getAllNotes(): List<Note> = notesDao.getAllNotes()


    suspend fun searchNoteByTitle(title: String): List<Note> = notesDao.getNoteByTitle("$title%")

    suspend fun getNoteById(noteId: Int): Note = notesDao.getNoteById(noteId)

    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

}