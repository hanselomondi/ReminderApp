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
import com.example.remindme.util.ScreenNames

@Composable
fun ReminderScreen(
    modifier: Modifier = Modifier,
    viewModel: ReminderViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val inCompleteReminderState by viewModel.incompleteReminderState.collectAsStateWithLifecycle()
    val completeReminderState by viewModel.completeReminderState.collectAsStateWithLifecycle()

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
                inCompleteReminderState = inCompleteReminderState,
                onFabClicked = { navController.navigate(route = ScreenNames.NewReminderScreen.name) },
                currentScreen = currentScreen,
                onButtonClicked = viewModel::onReminderButtonClicked,
                onHomeClicked = {
                    viewModel.getIncompleteReminders()
                },
                onCompletedClicked = {
                    navController.navigate(route = ScreenNames.CompletedRemindersScreen.name)
                    viewModel.getCompletedReminders()
                },
                onDeleteReminder = viewModel::onDeleteReminder
            )
        }

        composable(route = ScreenNames.CompletedRemindersScreen.name) {
            CompletedReminderScreen(
                modifier = modifier,
                completeReminderState = completeReminderState,
                onFabClicked = { navController.navigate(route = ScreenNames.NewReminderScreen.name) },
                currentScreen = currentScreen,
                onButtonClicked = viewModel::onReminderButtonClicked,
                onHomeClicked = {
                    navController.navigate(route = ScreenNames.HomeScreen.name)
                    viewModel.getIncompleteReminders()
                },
                onCompletedClicked = {
                    viewModel.getCompletedReminders()
                },
                onDeleteReminder = viewModel::onDeleteReminder
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

