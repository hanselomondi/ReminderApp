package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.remindme.R
import com.example.remindme.ui.theme.RemindMeTheme

/**
 * Displays the due date and due time information and selection buttons in the
 * CreateReminderScreen
 * @param text the selected date or time to be displayed
 * @param buttonLabel text used to label the button
 * @param onClickButton lambda to be executed when the button is clicked
 */
@Composable
fun CreateReminderDueTimeItem(
    text: String,
    buttonLabel: String,
    onClickButton: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onClickButton) {
            Text(text = buttonLabel)
        }
    }
}


@Preview(name = "DueTimeLight", showBackground = true)
@Composable
private fun DueTimeLightPreview() {
    RemindMeTheme(darkTheme = false) {
        CreateReminderDueTimeItem(
            text = "Due Date",
            buttonLabel = "Due Time",
            onClickButton = {}
        )
    }
}


@Preview(name = "DueTimeDark", showBackground = true)
@Composable
private fun DueTimeDarkPreview() {
    RemindMeTheme(darkTheme = true) {
        CreateReminderDueTimeItem(
            text = "Due Date",
            buttonLabel = "Due Time",
            onClickButton = {}
        )
    }
}