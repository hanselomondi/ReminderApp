package com.example.remindme.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toDateTimeString(pattern: String = "dd-MM-yyyy HH:mm:ss"): String {
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return dateTime.format(formatter)
}

fun Long.toDateString(): String {
    return this.toDateTimeString( "dd-MM-yyyy")
}

fun Long.toTimeString(): String {
    return this.toDateTimeString("HH:mm")
}

fun Long.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
    return Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .toLocalDate()
}

fun Long.toLocalTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalTime {
    return Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .toLocalTime()
}