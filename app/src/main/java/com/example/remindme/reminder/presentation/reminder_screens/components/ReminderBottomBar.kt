package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.remindme.R
import com.example.remindme.ui.theme.RemindMeTheme
import com.example.remindme.util.ScreenNames

data class BottomNavigationItem(
    val destination: ScreenNames,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
    val onClick: () -> Unit,
    val label: String
)


@Composable
fun ReminderBottomBar(
    modifier: Modifier = Modifier,
    currentScreen: ScreenNames,
    onHomeClicked: () -> Unit,
    onCompletedClicked: () -> Unit
) {
    val items = listOf(
        BottomNavigationItem(
            destination = ScreenNames.HomeScreen,
            filledIcon = Icons.Filled.Home,
            outlinedIcon = Icons.Outlined.Home,
            onClick = onHomeClicked,
            label = stringResource(R.string.home)
        ),
        BottomNavigationItem(
            destination = ScreenNames.CompletedRemindersScreen,
            filledIcon = Icons.Filled.CheckCircle,
            outlinedIcon = Icons.Outlined.CheckCircleOutline,
            onClick = onCompletedClicked,
            label = stringResource(R.string.completed)
        )
    )

    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentScreen == item.destination,
                onClick = item.onClick,
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            imageVector = if (currentScreen == item.destination) item.filledIcon else item.outlinedIcon,
                            contentDescription = item.destination.title
                        )
                    }
                },
                label = {
                    Text(text = item.label)
                }
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