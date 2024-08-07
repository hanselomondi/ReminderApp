package com.example.remindme.reminder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.remindme.reminder.domain.model.ReminderPriority

@Entity(tableName = "reminder_table")
data class ReminderEntity(
    val title: String = "",
    val description: String? = null,
    val dueDate: Long = 0L,
    val dueTime: Long? = null,
    val priority: ReminderPriority,
    val tags: List<String> = emptyList(),
    val createdAt: Long = 0L,
    val updatedAt: Long = 0L,
    val isCompleted: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
