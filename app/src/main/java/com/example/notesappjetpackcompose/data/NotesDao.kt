package com.example.notesappjetpackcompose.data

import androidx.room.*
import com.example.notesappjetpackcompose.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("select * from notes_table")
    fun getAllNotes(): Flow<List<Note>>

    @Query("select * from notes_table where id=:id")
    suspend fun getNoteById(id: String): Note

    @Query("Delete from notes_table")
    suspend fun deleteAllNotes()

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertNote(note: Note)

    @Update(onConflict =OnConflictStrategy.REPLACE )
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}
