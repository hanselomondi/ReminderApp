package com.example.remindme.util

import java.time.LocalDate
import java.time.ZoneId

fun LocalDate.toEpochMilli(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return this.atStartOfDay(zoneId).toInstant().toEpochMilli()
}