package com.example.remindme.reminder.data.repository

import com.example.remindme.reminder.data.local.ReminderDao
import com.example.remindme.reminder.data.mapper.toReminder
import com.example.remindme.reminder.data.mapper.toReminderEntity
import com.example.remindme.reminder.domain.model.Reminder
import com.example.remindme.reminder.domain.model.ReminderPriority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao
) {
    suspend fun addNewReminder(reminder: Reminder) {
        reminderDao.insertReminder(reminder.toReminderEntity())
    }

    suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder.toReminderEntity())
    }

    fun getAllReminders(): Flow<List<Reminder>> {
        return reminderDao.getAllReminders().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    suspend fun getReminderById(reminderId: Int): Reminder? {
        return reminderDao.getReminderById(reminderId)?.toReminder()
    }

    fun getRemindersSortedByDate(): Flow<List<Reminder>> {
        return reminderDao.getRemindersSortedByDate().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    fun getRemindersSortedByDateCreated(): Flow<List<Reminder>> {
        return reminderDao.getRemindersSortedByDateCreated().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    fun getIncompleteReminders(): Flow<List<Reminder>> {
        return reminderDao.getInCompleteReminders().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    fun getCompletedReminders(): Flow<List<Reminder>> {
        return reminderDao.getCompletedReminders().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    fun getRemindersByPriority(priority: ReminderPriority): Flow<List<Reminder>> {
        return reminderDao.getRemindersByPriority(priority).map { reminders ->
            reminders.map { it.toReminder() }
        }
    }
}