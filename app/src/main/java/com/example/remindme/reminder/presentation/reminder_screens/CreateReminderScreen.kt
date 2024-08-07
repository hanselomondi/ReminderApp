package com.example.remindme.reminder.presentation.reminder_screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remindme.R
import com.example.remindme.reminder.domain.model.Reminder
import com.example.remindme.reminder.domain.model.ReminderPriority
import com.example.remindme.reminder.presentation.reminder_screens.components.CreateReminderDueTimeItem
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderTopBar
import com.example.remindme.util.ScreenNames
import com.example.remindme.util.toDateString
import com.example.remindme.util.toEpochMilli
import com.example.remindme.util.toLocalDate
import com.example.remindme.util.toLocalTime
import com.example.remindme.util.toTimeString
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun CreateReminderScreen(
    modifier: Modifier = Modifier,
    currentScreen: ScreenNames,
    newReminderViewModel: NewReminderViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val newReminderState by newReminderViewModel.newReminderState.collectAsStateWithLifecycle()

    CreateReminderContent(
        modifier = modifier,
        currentScreen = currentScreen,
        title = newReminderState.title,
        onTitleChange = newReminderViewModel::onTitleChange,
        description = newReminderState.description ?: "",
        onDescriptionChange = newReminderViewModel::onDescriptionChange,
        dueDate = newReminderState.dueDate,
        onDateChange = newReminderViewModel::onDueDateChange,
        dueTime = newReminderState.dueTime,
        onTimeChange = newReminderViewModel::onDueTimeChange,
        onCancelClicked = {
            navController.popBackStack(
                route = ScreenNames.HomeScreen.title,
                inclusive = false
            )
        },
        onSaveClicked = {
            val newReminder = Reminder(
                title = newReminderState.title,
                description = newReminderState.description,
                dueDate = newReminderState.dueDate,
                dueTime = newReminderState.dueTime,
                priority = ReminderPriority.LOW
            )
            newReminderViewModel.addNewReminder(newReminder)
            navController.navigate(ScreenNames.HomeScreen.name)
        }
    )
}


@Composable
fun CreateReminderContent(
    modifier: Modifier = Modifier,
    currentScreen: ScreenNames,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    dueDate: Long,
    onDateChange: (LocalDate) -> Unit,
    dueTime: Long?,
    onTimeChange: (LocalTime) -> Unit,
    onCancelClicked: () -> Unit = {},
    onSaveClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = { ReminderTopBar(currentScreen = currentScreen) }
    ) { innerPadding ->
        // Format the current date into a string
        val formattedDate by remember { mutableStateOf(dueDate.toDateString()) }

        // Format the current time into a string
        val formattedTime by remember { mutableStateOf(dueTime!!.toTimeString()) }

        val dateDialogState = rememberMaterialDialogState()
        val timeDialogState = rememberMaterialDialogState()
        val context = LocalContext.current

        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.small_padding))
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { onTitleChange(it) },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = {
                        Text(
                            text = stringResource(R.string.task_title),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { onDescriptionChange(it) },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = {
                        Text(
                            text = stringResource(R.string.task_description),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extra_small_padding)))
                Column {
                    CreateReminderDueTimeItem(
                        text = formattedDate,
                        buttonLabel = stringResource(R.string.date_button_label),
                        onClickButton = {
                            dateDialogState.show()
                        }
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extra_small_padding)))
                    CreateReminderDueTimeItem(
                        text = formattedTime,
                        buttonLabel = stringResource(R.string.time_button_label),
                        onClickButton = {
                            timeDialogState.show()
                        }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = onCancelClicked,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.cancel_button))
                    }
                    TextButton(
                        onClick = onSaveClicked,
                        enabled = title.isNotEmpty() && description.isNotEmpty(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(R.string.save_button))
                    }
                }
            }
            /*if (newReminderViewModel.isSaving) {
            CircularProgressIndicator()
        }*/
        }

        // Define MaterialDialog for date picker
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(
                    text = stringResource(id = R.string.ok_button)
                ) {
                    Toast.makeText(
                        context,
                        "Date saved",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                negativeButton(
                    text = stringResource(id = R.string.cancel_button)
                )
            }
        ) {
            datepicker(
                title = stringResource(id = R.string.pick_date_title),
                initialDate = dueDate.toLocalDate(),
                allowedDateValidator = {
                    it >= LocalDate.now()
                },
                onDateChange = { onDateChange(it) }
            )
        }

        // Define MaterialDialog for time picker
        MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(
                    text = stringResource(id = R.string.ok_button)
                ) {
                    Toast.makeText(
                        context,
                        "Time saved",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                negativeButton(
                    text = stringResource(id = R.string.cancel_button)
                )
            }
        ) {
            timepicker(
                title = stringResource(id = R.string.time_picker_title),
                initialTime = dueTime?.toLocalTime() ?: LocalTime.now(),
                onTimeChange = { onTimeChange(it) }
            )
        }
    }
}
