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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.remindme.reminder.presentation.reminder_screens.components.EmptyScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.LoadingScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderListScreen
import com.example.remindme.reminder.presentation.reminder_screens.components.ReminderTopBar
import com.example.remindme.util.ScreenNames

@Composable
fun ReminderScreen(
    modifier: Modifier = Modifier,
    viewModel: ReminderViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val reminderState by viewModel.reminderState.collectAsStateWithLifecycle()

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

