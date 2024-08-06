package com.example.remindme.reminder.data.local

import androidx.room.TypeConverter
import com.example.remindme.reminder.domain.model.ReminderPriority

class Converters {
    @TypeConverter
    fun toReminderPriority(value: String): ReminderPriority {
        return ReminderPriority.valueOf(value) // Returns the enum constant with the specified name
    }

    @TypeConverter
    fun fromReminderPriority(priority: ReminderPriority): String {
        return priority.value // Returns the string representation of the enum constant
    }

    @TypeConverter
    fun toTags(dbString: String): List<String> {
        return dbString.split(",")
    }

    @TypeConverter
    fun fromTags(tags: List<String>): String {
        return tags.joinToString(separator = ",")
    }
}