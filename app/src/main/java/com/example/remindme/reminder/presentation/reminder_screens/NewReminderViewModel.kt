package com.example.remindme.reminder.presentation.reminder_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindme.reminder.domain.model.Reminder
import com.example.remindme.reminder.domain.repository.ReminderRepository
import com.example.remindme.util.toEpochMilli
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class NewReminderViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository
): ViewModel() {
    private val _newReminderState = MutableStateFlow(NewReminderState())
    val newReminderState: StateFlow<NewReminderState> = _newReminderState.asStateFlow()

    private fun onResetClicked() {
        _newReminderState.value = NewReminderState()
    }

    fun onTitleChange(value: String) {
        _newReminderState.update {
            it.copy(title = value)
        }
    }

    fun onDescriptionChange(value: String) {
        _newReminderState.update {
            it.copy(description = value)
        }
    }

    fun onDueDateChange(value: LocalDate) {
        _newReminderState.update {
            it.copy(dueDate = value.toEpochMilli())
        }
    }

    fun onDueTimeChange(value: LocalTime) {
        _newReminderState.update {
            it.copy(dueTime = value.toEpochMilli())
        }
    }

    fun addNewReminder(reminder: Reminder) = viewModelScope.launch {
        reminderRepository.addNewReminder(reminder)
    }
}