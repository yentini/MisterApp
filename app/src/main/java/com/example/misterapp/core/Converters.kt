package com.example.misterapp.core

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let {
            LocalDate.ofEpochDay(value)
        }
    }

    @TypeConverter
    fun LocalDateTimeToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}