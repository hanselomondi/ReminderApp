package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.remindme.reminder.domain.model.Reminder

@Composable
fun ReminderListScreen(
    modifier: Modifier = Modifier,
    reminders: List<Reminder>
) {
    LazyColumn(modifier = modifier) {
        items(reminders) { reminder ->
            ReminderCard(
                title = reminder.title,
                description = reminder.description,
                dueDateTime = "reminder.dueDate",
                onButtonClicked = {}
            )
        }
    }
}