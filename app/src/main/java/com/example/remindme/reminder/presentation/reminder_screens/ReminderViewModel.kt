package com.example.remindme.reminder.presentation.reminder_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindme.reminder.domain.repository.ReminderRepository
import com.example.remindme.util.Constants
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

    /**
     * StateFlow to determine whether the user is viewing Completed or Incomplete reminders
     * while still in the HomeScreen
     */
    private val _homeScreenState = MutableStateFlow(Constants.INCOMPLETE_REMINDERS)
    val homeScreenState: StateFlow<Constants> = _homeScreenState.asStateFlow()

    init {
        getIncompleteReminders()
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

    fun getIncompleteReminders() = viewModelScope.launch {
        reminderRepository.getIncompleteReminders().collect { reminders ->
            if (reminders.isEmpty()) {
                _reminderState.value = ReminderViewState.Empty
            } else {
                _reminderState.value = ReminderViewState.Success(reminders)
            }
        }
    }

    fun getCompletedReminders() = viewModelScope.launch {
        reminderRepository.getCompletedReminders().collect { reminders ->
            if (reminders.isEmpty()) {
                _reminderState.value = ReminderViewState.Empty
            } else {
                _reminderState.value = ReminderViewState.Success(reminders)
            }
        }
    }

    fun onReminderButtonClicked(reminderId: Int, isCompleted: Boolean) = viewModelScope.launch {
        reminderRepository.updateReminderStatus(reminderId, isCompleted)
    }

    fun onHomeScreenStateChanged(homeScreenState: Constants) {
        _homeScreenState.value = homeScreenState
    }
}