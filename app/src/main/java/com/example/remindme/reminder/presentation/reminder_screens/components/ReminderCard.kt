package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.remindme.R
import com.example.remindme.ui.theme.RemindMeTheme

@Composable
fun ReminderCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    dueDateTime: String,
    isCompleted: Boolean,
    onButtonClicked: () -> Unit,
    onDeleteReminder: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        var showDialog by rememberSaveable { mutableStateOf(false) }

        Card(modifier = Modifier.fillMaxSize()) {
            val textDecoration =
                if (isCompleted) TextDecoration.LineThrough else TextDecoration.None

            Row(
                modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding))
            ) {
                if (isCompleted) {
                    IconButton(
                        onClick = onButtonClicked
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null
                        )
                    }
                } else {
                    RadioButton(
                        selected = false,
                        onClick = onButtonClicked
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small_padding))
                        .weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        textDecoration = textDecoration
                    )
                    Text(
                        text = description ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        textDecoration = textDecoration
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                    Text(
                        text = dueDateTime,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = textDecoration
                    )
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Reminder"
                    )
                }
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = !showDialog },
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.small_padding))
                    ) {
                        Text(
                            text = stringResource(R.string.delete_dialog_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.small_padding))
                    ) {
                        Text(
                            text = stringResource(R.string.delete_prompt),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog != showDialog
                            onDeleteReminder()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.confirm_button),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            )
        }
    }
}


@Preview(name = "ReminderCardLight", showBackground = true)
@Composable
private fun ReminderCardLightPreview() {
    RemindMeTheme(darkTheme = false) {
        ReminderCard(
            title = "Title",
            description = "Description",
            dueDateTime = "DateTime",
            onButtonClicked = {},
            onDeleteReminder = {},
            isCompleted = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(name = "ReminderCardDark", showBackground = true)
@Composable
private fun ReminderScreenDarkPreview() {
    RemindMeTheme(darkTheme = true) {
        ReminderCard(
            title = "Title",
            description = "Description",
            dueDateTime = "DateTime",
            onButtonClicked = {},
            onDeleteReminder = {},
            isCompleted = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}