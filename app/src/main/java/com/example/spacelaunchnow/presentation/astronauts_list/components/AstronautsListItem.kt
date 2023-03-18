package com.example.spacelaunchnow.presentation.astronauts_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.spacelaunchnow.domain.model.Astronaut

@Composable
fun AstronautListItem(
    astronaut: Astronaut, onItemClick: (Astronaut) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(16.dp, 4.dp))
            .clickable { onItemClick(astronaut) },
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(astronaut.profileImageThumbnail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(CenterVertically)
                    .size(128.dp)
                    .padding(PaddingValues(16.dp, 16.dp))
                    .clip(CircleShape)

            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = astronaut.name, style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "First Flight: ${astronaut.firstFlight}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Last Flight: ${astronaut.lastFlight}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Nationality: ${astronaut.nationality}",
                    style = MaterialTheme.typography.bodySmall
                )
                if (astronaut.dateOfBirth.isNotBlank()) Text(
                    text = "Date of Birth: ${astronaut.dateOfBirth}",
                    style = MaterialTheme.typography.bodySmall
                )
                if (astronaut.dateOfDeath.isNotBlank()) Text(
                    text = "Date of Death: ${astronaut.dateOfDeath}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}