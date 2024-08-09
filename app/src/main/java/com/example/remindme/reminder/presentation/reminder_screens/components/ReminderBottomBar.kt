package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BadgedBox
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remindme.R
import com.example.remindme.ui.theme.RemindMeTheme
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
                icon = if (currentScreen == ScreenNames.CompletedRemindersScreen) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircleOutline,
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
    BadgedBox(badge = {}, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = onIconClicked
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Preview(name = "BottomBarDark", showBackground = true)
@Composable
private fun BottomBarDarkPreview() {
    RemindMeTheme(darkTheme = true) {
        ReminderBottomBar(
            currentScreen = ScreenNames.HomeScreen,
            onHomeClicked = {},
            onCompletedClicked = {}
        )
    }
}


@Preview(name = "BottomBarLight", showBackground = true)
@Composable
private fun BottomBarLightPreview() {
    RemindMeTheme(darkTheme = false) {
        ReminderBottomBar(
            currentScreen = ScreenNames.HomeScreen,
            onHomeClicked = {},
            onCompletedClicked = {}
        )
    }
}


@Preview(name = "BottomRowItemDark", showBackground = true)
@Composable
private fun BottomRowItemDarkPreview() {
    RemindMeTheme(darkTheme =  true) {
        BottomRowItem(
            icon = Icons.Filled.Home,
            label = "Home",
            onIconClicked = {}
        )
    }
}


@Preview(name = "BottomRowItemLight", showBackground = true)
@Composable
private fun BottomRowItemLightPreview() {
    RemindMeTheme(darkTheme =  true) {
        BottomRowItem(
            icon = Icons.Filled.Home,
            label = "Home",
            onIconClicked = {}
        )
    }
}