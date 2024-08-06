package com.example.remindme.reminder.domain.repository

import com.example.remindme.reminder.domain.model.Reminder
import com.example.remindme.reminder.domain.model.ReminderPriority
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    suspend fun insertReminder(reminder: Reminder)

    suspend fun deleteReminder(reminder: Reminder)

    suspend fun getAllReminders(): List<Reminder>

    suspend fun getRemindersByPriority(priority: ReminderPriority): List<Reminder>?

    suspend fun getReminderById(id: Int): Reminder?

    fun getRemindersSortedByDate(): Flow<List<Reminder>>

    fun getRemindersSortedByDateCreated(): Flow<List<Reminder>>

    fun getInCompleteReminders(): Flow<List<Reminder>>

    fun getCompletedReminders(): Flow<List<Reminder>>
}