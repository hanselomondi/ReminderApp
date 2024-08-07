package com.example.remindme.reminder.domain.repository

import com.example.remindme.reminder.domain.model.Reminder
import com.example.remindme.reminder.domain.model.ReminderPriority
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    suspend fun addNewReminder(reminder: Reminder)

    suspend fun deleteReminder(reminder: Reminder)

    fun getAllReminders(): Flow<List<Reminder>>

    fun getRemindersByPriority(priority: ReminderPriority): Flow<List<Reminder>?>

    suspend fun getReminderById(reminderId: Int): Reminder?

    fun getRemindersSortedByDate(): Flow<List<Reminder>>

    fun getRemindersSortedByDateCreated(): Flow<List<Reminder>>

    fun getIncompleteReminders(): Flow<List<Reminder>>

    fun getCompletedReminders(): Flow<List<Reminder>>
}