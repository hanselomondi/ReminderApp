package com.example.remindme.reminder.presentation.reminder_screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.remindme.R
import com.example.remindme.ui.theme.RemindMeTheme

@Composable
fun ReminderCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    dueDateTime: String,
    isCompleted: Boolean,
    onButtonClicked: () -> Unit
) {
    Card(modifier = modifier) {
        val textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None

        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding))
        ) {
            if (isCompleted) {
                IconButton(
                    onClick = onButtonClicked
                ) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = null
                    )
                }
            } else {
                RadioButton(
                    selected = false,
                    onClick =  onButtonClicked
                )
            }
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.small_padding))
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = textDecoration
                )
                Text(
                    text = description ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = textDecoration
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                Text(
                    text = dueDateTime,
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = textDecoration
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
            isCompleted = false,
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
            isCompleted = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}