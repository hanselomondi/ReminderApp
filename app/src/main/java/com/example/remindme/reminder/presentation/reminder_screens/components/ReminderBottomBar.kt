package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.remindme.R
import com.example.remindme.util.ScreenNames

@Composable
fun ReminderBottomBar(
    modifier: Modifier = Modifier,
    currentScreen: ScreenNames,
    onHomeClicked: () -> Unit,
    onCompletedClicked: () -> Unit
) {
    BottomAppBar(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomRowItem(
                icon = if (currentScreen == ScreenNames.HomeScreen) Icons.Filled.Home else Icons.Outlined.Home,
                label = stringResource(R.string.home),
                onIconClicked = { onHomeClicked() }
            )
            BottomRowItem(
                icon = if (currentScreen == ScreenNames.NewReminderScreen) Icons.Filled.Checklist else Icons.Outlined.Checklist,
                label = stringResource(R.string.completed),
                onIconClicked = { onCompletedClicked() }
            )
        }
    }
}


@Composable
fun BottomRowItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    onIconClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onIconClicked
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.extra_small_padding)))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}