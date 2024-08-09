package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.remindme.R
import com.example.remindme.reminder.presentation.reminder_screens.ReminderViewState
import com.example.remindme.ui.theme.RemindMeTheme
import com.example.remindme.util.ScreenNames

@Composable
fun HomeSubScreenContent(
    modifier: Modifier = Modifier,
    currentScreen: ScreenNames,
    reminderState: ReminderViewState,
    onButtonClicked: (Int, Boolean) -> Unit,
    onFabClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onCompletedClicked: () -> Unit
) {
    Scaffold(
        topBar = { ReminderTopBar(currentScreen = currentScreen) },
        bottomBar = {
            ReminderBottomBar(
                currentScreen = currentScreen,
                onHomeClicked = onHomeClicked,
                onCompletedClicked = onCompletedClicked
            )
        },
        floatingActionButton = {
            ReminderFAB(onFabClicked = onFabClicked)
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
                        message = reminderState.exception.message ?: stringResource(R.string.screen_view_state_error_message),
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


@Preview(name = "HomeSubScreenContentDark", showBackground = true)
@Composable
private fun HomeSubScreenContentDarkPreview() {
    RemindMeTheme(darkTheme = true) {
        HomeSubScreenContent(
            currentScreen = ScreenNames.HomeScreen,
            reminderState = ReminderViewState.Loading,
            onButtonClicked = { _, _ -> },
            onFabClicked = {},
            onHomeClicked = {},
            onCompletedClicked = {}
        )
    }
}


@Preview(name = "HomeSubScreenContentLight", showBackground = true)
@Composable
private fun HomeSubScreenContentLightPreview() {
    RemindMeTheme(darkTheme = false) {
        HomeSubScreenContent(
            currentScreen = ScreenNames.HomeScreen,
            reminderState = ReminderViewState.Loading,
            onButtonClicked = { _, _ -> },
            onFabClicked = {},
            onHomeClicked = {},
            onCompletedClicked = {}
        )
    }
}