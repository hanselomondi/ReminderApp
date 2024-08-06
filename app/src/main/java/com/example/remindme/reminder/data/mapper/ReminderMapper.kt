package com.example.remindme.reminder.data.mapper

import com.example.remindme.reminder.data.local.ReminderEntity
import com.example.remindme.reminder.domain.model.Reminder

fun ReminderEntity.toReminder(): Reminder {
    return Reminder(
        id = this.id,
        title = this.title,
        description = this.description,
        dueDate = this.dueDate,
        dueTime = this.dueTime,
        priority = this.priority,
        tags = this.tags,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        isCompleted = this.isCompleted
    )
}

fun Reminder.toReminderEntity(): ReminderEntity {
    return ReminderEntity(
        title = this.title,
        description = this.description,
        dueDate = this.dueDate,
        dueTime = this.dueTime,
        priority = this.priority,
        tags = this.tags,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        isCompleted = this.isCompleted
    )
}