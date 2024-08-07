package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier)
}