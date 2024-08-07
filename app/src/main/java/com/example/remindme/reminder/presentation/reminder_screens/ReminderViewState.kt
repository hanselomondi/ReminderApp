package com.example.remindme.reminder.presentation.reminder_screens

import com.example.remindme.reminder.domain.model.Reminder

sealed interface ReminderViewState {
    data class Success(val reminders: List<Reminder>): ReminderViewState
    data class Error(val exception: Exception): ReminderViewState
    object Loading: ReminderViewState
    object Empty: ReminderViewState
}