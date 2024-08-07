package com.example.remindme.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun LocalTime.toEpochMilli(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return ZonedDateTime.of(LocalDate.ofEpochDay(0), this, zoneId)
        .toInstant()
        .toEpochMilli()
}