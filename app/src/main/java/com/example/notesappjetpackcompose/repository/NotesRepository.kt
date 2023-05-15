package com.example.notesappjetpackcompose.repository

import com.example.notesappjetpackcompose.data.NotesDao
import com.example.notesappjetpackcompose.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotesRepository @Inject constructor(val notesDao: NotesDao) {
    suspend fun addNote(note: Note) = notesDao.insertNote(note = note)
    fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
    suspend fun deleteAllNote()= notesDao.deleteAllNotes()
    suspend fun deleteNote(noteId: Note)= notesDao.deleteNote(noteId)
    suspend fun updateNote(note: Note)= notesDao.updateNote(note =note)
}