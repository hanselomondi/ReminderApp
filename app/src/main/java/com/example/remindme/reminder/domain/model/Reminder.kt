package com.example.remindme.reminder.domain.model

data class Reminder(
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val dueDate: Long,
    val dueTime: Long? = null,
    val priority: ReminderPriority,
    val tags: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)
