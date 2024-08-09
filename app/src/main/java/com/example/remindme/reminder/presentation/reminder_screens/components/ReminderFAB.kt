package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReminderFAB(
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = onFabClicked,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = null
        )
    }
}