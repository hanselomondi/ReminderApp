package com.example.remindme.reminder.data.repository

import com.example.remindme.reminder.data.local.ReminderDao
import com.example.remindme.reminder.data.mapper.toReminder
import com.example.remindme.reminder.data.mapper.toReminderEntity
import com.example.remindme.reminder.domain.model.Reminder
import com.example.remindme.reminder.domain.model.ReminderPriority
import com.example.remindme.reminder.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao
): ReminderRepository {
    override suspend fun addNewReminder(reminder: Reminder) {
        reminderDao.insertReminder(reminder.toReminderEntity())
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder.toReminderEntity())
    }

    override fun getAllReminders(): Flow<List<Reminder>> {
        return reminderDao.getAllReminders().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    override suspend fun getReminderById(reminderId: Int): Reminder? {
        return reminderDao.getReminderById(reminderId)?.toReminder()
    }

    override fun getRemindersSortedByDate(): Flow<List<Reminder>> {
        return reminderDao.getRemindersSortedByDate().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    override fun getRemindersSortedByDateCreated(): Flow<List<Reminder>> {
        return reminderDao.getRemindersSortedByDateCreated().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    override fun getIncompleteReminders(): Flow<List<Reminder>> {
        return reminderDao.getInCompleteReminders().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    override fun getCompletedReminders(): Flow<List<Reminder>> {
        return reminderDao.getCompletedReminders().map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    override fun getRemindersByPriority(priority: ReminderPriority): Flow<List<Reminder>?> {
        return reminderDao.getRemindersByPriority(priority).map { reminders ->
            reminders.map { it.toReminder() }
        }
    }

    override suspend fun updateReminderStatus(id: Int, isCompleted: Boolean) {
        reminderDao.updateReminderStatus(id, isCompleted)
    }

    override suspend fun deleteReminder(id: Int) {
        reminderDao.deleteReminder(id)
    }
}