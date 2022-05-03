package com.muhammed.ontime.viewmodel

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
class NotesViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {
    private val _notesList = MutableStateFlow<List<Note>>(emptyList())
    val notesList: StateFlow<List<Note>> = _notesList

    fun loadNotes() {
        viewModelScope.launch {
            _notesList.value = repository.getAllNotes().sortByPin()
        }
    }

    fun searchNotesByTitle(title: String) {
        viewModelScope.launch {
            repository.searchNoteByTitle(title).also {
                if (it.isNotEmpty()) {
                    _notesList.value = it.sortByPin()
                }
            }
        }
    }

    private fun List<Note>.sortByPin(): List<Note> = this.sortedByDescending { it.isPinned }
}