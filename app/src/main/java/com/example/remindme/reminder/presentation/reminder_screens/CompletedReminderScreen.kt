package com.example.remindme.reminder.presentation.reminder_screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.remindme.reminder.presentation.reminder_screens.components.HomeSubScreenContent
import com.example.remindme.util.ScreenNames

@Composable
fun CompletedReminderScreen(
    modifier: Modifier = Modifier,
    completeReminderState: ReminderViewState,
    onFabClicked: () -> Unit,
    currentScreen: ScreenNames,
    onButtonClicked: (Int, Boolean) -> Unit,
    onHomeClicked: () -> Unit,
    onCompletedClicked: () -> Unit
) {
    HomeSubScreenContent(
        currentScreen = currentScreen,
        reminderState = completeReminderState,
        onFabClicked = onFabClicked,
        onButtonClicked = onButtonClicked,
        onHomeClicked = onHomeClicked,
        onCompletedClicked = onCompletedClicked,
        modifier = modifier
    )
}