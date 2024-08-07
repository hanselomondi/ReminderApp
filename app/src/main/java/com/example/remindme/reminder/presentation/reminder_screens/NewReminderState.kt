package com.example.remindme.reminder.presentation.reminder_screens

import com.example.remindme.reminder.domain.model.ReminderPriority

data class NewReminderState(
    val title: String = "",
    val description: String? = null,
    val dueDate: Long = System.currentTimeMillis(),
    val dueTime: Long? = System.currentTimeMillis(),
    val priority: ReminderPriority = ReminderPriority.LOW,
    val tags: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)
