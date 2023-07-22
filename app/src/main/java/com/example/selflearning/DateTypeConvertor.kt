package com.example.selflearning

import androidx.room.TypeConverter
import java.util.Date


class DateTypeConvertor {
    @TypeConverter
    fun fromDateToLong(date: Date):Long{
        return date.time
    }
    @TypeConverter
    fun fromLongToDate(value: Long):Date{
        return Date(value)
    }
}