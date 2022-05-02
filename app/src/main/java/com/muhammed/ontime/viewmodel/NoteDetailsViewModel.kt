package com.muhammed.ontime.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammed.ontime.datasource.NotesRepository
import com.muhammed.ontime.pojo.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(private val notesRepo: NotesRepository) :
    ViewModel() {
    private val _noteTitle = MutableStateFlow<String?>(null)
    val noteTitle: StateFlow<String?> = _noteTitle
    private val _noteText = MutableStateFlow<String?>(null)
    val noteText: StateFlow<String?> = _noteText
    private val _pinStatus = MutableStateFlow(false)
    val pinStatus: StateFlow<Boolean> = _pinStatus
    private val _note = MutableStateFlow(Note(null, ""))
    val note: StateFlow<Note> = _note


    fun saveTitle(title: String) {
        viewModelScope.launch {
            _noteTitle.value = title
            _note.value.title = title
        }
    }

    fun saveText(text: String) {
        viewModelScope.launch {
            _noteText.value = text
            _note.value.text = text
        }
    }


    fun togglePinStatus() {
        viewModelScope.launch {
            _note.value.isPinned = !_note.value.isPinned
            _pinStatus.value = _note.value.isPinned
        }
    }

    // If User is editing an existing note, load it and start editing it.
    fun loadNoteByIdIfEditing(id: Int) {
        if (id != -1) {
            viewModelScope.launch {
                _note.value = notesRepo.getNoteById(id).also {
                    _noteText.value = it.text
                    _noteTitle.value = it.title
                    Log.d(TAG, "loadNoteByIdIfEditing: $it")
                }
            }
        }
    }


    fun saveNote() {
        viewModelScope.launch {
            notesRepo.saveNote(_note.value)
        }
    }

    companion object {
        private const val TAG = "NotesViewModel"
    }


}