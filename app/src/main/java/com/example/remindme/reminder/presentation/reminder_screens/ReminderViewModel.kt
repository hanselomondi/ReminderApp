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
    private val _reminderState = MutableStateFlow<ReminderViewState>(ReminderViewState.Loading)
    val reminderState: StateFlow<ReminderViewState> = _reminderState.asStateFlow()

    init {
        getAllReminders()
    }

    private fun getAllReminders() = viewModelScope.launch {
        reminderRepository.getAllReminders().collect { reminders ->
            if (reminders.isEmpty()) {
                _reminderState.value = ReminderViewState.Empty
            } else {
                _reminderState.value = ReminderViewState.Success(reminders)
            }
        }
    }
}