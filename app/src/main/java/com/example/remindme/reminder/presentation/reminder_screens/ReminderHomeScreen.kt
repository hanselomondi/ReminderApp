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
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.remindme.R
import com.example.remindme.reminder.presentation.reminder_screens.components.EmptyScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.LoadingScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderBottomBar
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderListScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderTopBar
import com.example.remindme.util.Constants
import com.example.remindme.util.ScreenNames


@Composable
fun ReminderHomeScreen(
    reminderState: ReminderViewState,
    navController: NavHostController,
    currentScreen: ScreenNames,
    homeScreenState: Constants,
    onButtonClicked: (Int, Boolean) -> Unit,
    onHomeClicked: () -> Unit,
    onCompletedClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { ReminderTopBar(currentScreen = currentScreen) },
        bottomBar = {
            ReminderBottomBar(
                currentScreen = currentScreen,
                homeScreenState = homeScreenState,
                onHomeClicked = onHomeClicked,
                onCompletedClicked = onCompletedClicked
            )
        },
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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.medium_padding)),
                        reminders = reminderState.reminders,
                        onButtonClicked = onButtonClicked
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