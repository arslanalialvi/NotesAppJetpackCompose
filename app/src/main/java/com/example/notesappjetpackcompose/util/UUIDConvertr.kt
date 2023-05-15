package com.example.notesappjetpackcompose.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConvertr {
    @TypeConverter
    fun fromUUID(uuid: UUID): String?{
        return uuid.toString()
    }
    @TypeConverter
    fun toUUID(value: String?): UUID? = UUID.fromString(value)
}