package com.example.remindme.reminder.presentation.reminder_screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.remindme.util.Constants
import com.example.remindme.util.ScreenNames

@Composable
fun ReminderScreen(
    modifier: Modifier = Modifier,
    viewModel: ReminderViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val reminderState by viewModel.reminderState.collectAsStateWithLifecycle()
    val homeScreenState by viewModel.homeScreenState.collectAsStateWithLifecycle()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ScreenNames.valueOf(
        backStackEntry?.destination?.route ?: ScreenNames.HomeScreen.name
    )

    NavHost(
        navController = navController,
        startDestination = ScreenNames.HomeScreen.name
    ) {
        composable(route = ScreenNames.HomeScreen.name) {
            ReminderHomeScreen(
                modifier = modifier,
                reminderState = reminderState,
                navController = navController,
                currentScreen = currentScreen,
                homeScreenState = homeScreenState,
                onButtonClicked = viewModel::onReminderButtonClicked,
                onHomeClicked = {
                    viewModel.onHomeScreenStateChanged(homeScreenState = Constants.INCOMPLETE_REMINDERS)
                    viewModel.getIncompleteReminders()
                },
                onCompletedClicked = {
                    viewModel.onHomeScreenStateChanged(homeScreenState = Constants.COMPLETED_REMINDERS)
                    viewModel.getCompletedReminders()
                }
            )
        }

        composable(route = ScreenNames.NewReminderScreen.name) {
            CreateReminderScreen(
                modifier = modifier,
                currentScreen = currentScreen,
                navController = navController
            )
        }
    }
}

