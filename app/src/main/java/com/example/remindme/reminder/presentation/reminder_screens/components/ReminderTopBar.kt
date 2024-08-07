package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.remindme.util.ScreenNames

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderTopBar(
    modifier: Modifier = Modifier,
    currentScreen: ScreenNames
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = currentScreen.title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}