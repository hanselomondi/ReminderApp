package com.example.remindme.reminder.presentation.reminder_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.remindme.reminder.presentation.reminder_screens.components.EmptyScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.LoadingScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderListScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderTopBar
import com.example.remindme.util.ScreenNames


@Composable
fun ReminderHomeScreen(
    reminderState: ReminderViewState,
    navController: NavHostController,
    currentScreen: ScreenNames,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { ReminderTopBar(currentScreen = currentScreen) },
        bottomBar = {},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ScreenNames.NewReminderScreen.name)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when(reminderState) {
                is ReminderViewState.Loading -> {
                    LoadingScreen()
                }
                is ReminderViewState.Success -> {
                    ReminderListScreen(
                        modifier = Modifier.fillMaxSize(),
                        reminders = reminderState.reminders
                    )
                }
                is ReminderViewState.Error -> {
                    EmptyScreen(
                        message = reminderState.exception.message ?: "Unknown error",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is ReminderViewState.Empty -> {
                    EmptyScreen(
                        message = "No reminders found",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}