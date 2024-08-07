package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.remindme.R
import com.example.remindme.ui.theme.RemindMeTheme

@Composable
fun ReminderCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    dueDateTime: String,
    onButtonClicked: () -> Unit
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding))
        ) {
            RadioButton(
                selected = false,
                onClick =  onButtonClicked
            )
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.small_padding))
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = description ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                Text(
                    text = dueDateTime,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


@Preview(name = "ReminderCardLight", showBackground = true)
@Composable
private fun ReminderCardLightPreview() {
    RemindMeTheme(darkTheme = false) {
        ReminderCard(
            title = "Title",
            description = "Description",
            dueDateTime = "DateTime",
            onButtonClicked = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(name = "ReminderCardDark", showBackground = true)
@Composable
private fun ReminderScreenDarkPreview() {
    RemindMeTheme(darkTheme = true) {
        ReminderCard(
            title = "Title",
            description = "Description",
            dueDateTime = "DateTime",
            onButtonClicked = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}