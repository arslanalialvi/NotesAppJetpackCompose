package com.example.notesappjetpackcompose.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappjetpackcompose.data.NotesDataSource
import com.example.notesappjetpackcompose.model.Note
import com.example.notesappjetpackcompose.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepo: NotesRepository) : ViewModel() {
    //val notes = mutableStateListOf<Note>();
    val _notes= MutableStateFlow<List<Note>>(emptyList())
    val notes= _notes.asStateFlow()

    init {
        viewModelScope.launch {
            notesRepo.getAllNotes().distinctUntilChanged().collect{
                notesList->
                if (notesList.isNullOrEmpty()){
                    Log.d("Empty", "Log list is empty")
                }
                else{
                    _notes.value= notesList               }
            }
        }
    }

     fun addNote(note: Note) = viewModelScope.launch { notesRepo.addNote(note) }
     fun deleteNote(noteId: Note) = viewModelScope.launch { notesRepo.deleteNote(noteId) }
     fun deleteAllNote() = viewModelScope.launch { notesRepo.deleteAllNote() }
     fun updateNote(note: Note) = viewModelScope.launch { notesRepo.updateNote(note) }

}
