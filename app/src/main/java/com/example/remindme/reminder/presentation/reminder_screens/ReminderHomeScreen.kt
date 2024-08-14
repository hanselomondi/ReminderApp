package com.example.remindme.reminder.presentation.reminder_screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.remindme.reminder.presentation.reminder_screens.components.HomeSubScreenContent
import com.example.remindme.util.ScreenNames


@Composable
fun ReminderHomeScreen(
    currentScreen: ScreenNames,
    inCompleteReminderState: ReminderViewState,
    onFabClicked: () -> Unit,
    onButtonClicked: (Int, Boolean) -> Unit,
    onHomeClicked: () -> Unit,
    onCompletedClicked: () -> Unit,
    onDeleteReminder: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeSubScreenContent(
        currentScreen = currentScreen,
        reminderState = inCompleteReminderState,
        onFabClicked = onFabClicked,
        onButtonClicked = onButtonClicked,
        onHomeClicked = onHomeClicked,
        onCompletedClicked = onCompletedClicked,
        onDeleteReminder = onDeleteReminder,
        modifier = modifier,
    )
}