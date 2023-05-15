package com.example.notesappjetpackcompose.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
@Entity(tableName = "notes_table")
data class Note @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo("notes_title")
    val title: String,

    @ColumnInfo("notes_description")
    val description: String,

    @ColumnInfo("notes_entry_date")
    val entryDate: Date = Date.from(Instant.now()))
