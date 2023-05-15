package com.example.notesappjetpackcompose.util

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.Date

class DateConverter {
    @TypeConverter
    fun dateToLong( localDateTime: Date): Long?=  localDateTime.time

    @TypeConverter
    fun longToDate(timeStamp: Long?): Date?= Date(timeStamp!!)
}