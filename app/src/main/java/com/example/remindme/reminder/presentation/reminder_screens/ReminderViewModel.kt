package com.example.remindme.reminder.presentation.reminder_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindme.reminder.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository
): ViewModel() {
    private val _completeReminderState = MutableStateFlow<ReminderViewState>(ReminderViewState.Loading)
    val completeReminderState: StateFlow<ReminderViewState> = _completeReminderState.asStateFlow()

    private val _incompleteReminderState = MutableStateFlow<ReminderViewState>(ReminderViewState.Loading)
    val incompleteReminderState: StateFlow<ReminderViewState> = _incompleteReminderState.asStateFlow()

    init {
        getIncompleteReminders()
    }

    private fun getAllReminders() = viewModelScope.launch {
        reminderRepository.getAllReminders().collect { reminders ->
            if (reminders.isEmpty()) {
                _incompleteReminderState.value = ReminderViewState.Empty
            } else {
                _incompleteReminderState.value = ReminderViewState.Success(reminders)
            }
        }
    }

    fun getIncompleteReminders() = viewModelScope.launch {
        reminderRepository.getIncompleteReminders().collect { reminders ->
            if (reminders.isEmpty()) {
                _incompleteReminderState.value = ReminderViewState.Empty
            } else {
                _incompleteReminderState.value = ReminderViewState.Success(reminders)
            }
        }
    }

    fun getCompletedReminders() = viewModelScope.launch {
        reminderRepository.getCompletedReminders().collect { reminders ->
            if (reminders.isEmpty()) {
                _completeReminderState.value = ReminderViewState.Empty
            } else {
                _completeReminderState.value = ReminderViewState.Success(reminders)
            }
        }
    }

    fun onReminderButtonClicked(reminderId: Int, isCompleted: Boolean) = viewModelScope.launch {
        reminderRepository.updateReminderStatus(reminderId, isCompleted)
    }
}