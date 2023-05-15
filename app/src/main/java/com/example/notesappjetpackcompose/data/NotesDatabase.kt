package com.example.notesappjetpackcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesappjetpackcompose.model.Note
import com.example.notesappjetpackcompose.util.DateConverter
import com.example.notesappjetpackcompose.util.UUIDConvertr

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConvertr::class)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NotesDao
}