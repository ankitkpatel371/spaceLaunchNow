package com.example.spacelaunchnow.presentation.astronauts_details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
fun TitleDescriptionItem(title: String, description: String) {
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontStyle = FontStyle.Italic
    )
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
    )
    Spacer(modifier = Modifier.height(2.dp))
    Divider()
}