package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.remindme.R
import com.example.remindme.reminder.domain.model.Reminder
import com.example.remindme.util.toFriendlyDateTimeString

@Composable
fun ReminderListScreen(
    modifier: Modifier = Modifier,
    reminders: List<Reminder>,
    onButtonClicked: (Int, Boolean) -> Unit,
    onDeleteReminder: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(reminders) { reminder ->
            ReminderCard(
                title = reminder.title,
                description = reminder.description,
                dueDateTime = reminder.dueDate.toFriendlyDateTimeString(reminder.dueTime ?: 0L),
                onButtonClicked = { onButtonClicked(reminder.id, !reminder.isCompleted) },
                onDeleteReminder = { onDeleteReminder(reminder.id) },
                isCompleted = reminder.isCompleted,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
        }
    }
}