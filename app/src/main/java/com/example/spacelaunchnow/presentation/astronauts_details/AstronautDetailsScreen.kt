package com.example.spacelaunchnow.presentation.astronauts_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.spacelaunchnow.domain.model.AstronautDetails
import com.example.spacelaunchnow.domain.model.Flight
import com.example.spacelaunchnow.presentation.astronauts_details.components.Divider
import com.example.spacelaunchnow.presentation.astronauts_details.components.FlightItem
import com.example.spacelaunchnow.presentation.astronauts_details.components.TitleDescriptionItem

@Composable
fun AstronautDetailsScreen(
    viewModel: AstronautsDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        state.astronautDetails?.let { astronautDetails ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                ProfileHeader(
                    profileURL = astronautDetails.profileImage
                )
                setAstronautDetailsScreen(astronaut = astronautDetails)
            }

        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun ProfileHeader(
    profileURL: String
) {
    Image(
        modifier = Modifier
            .heightIn(max = 350.dp)
            .fillMaxWidth(),
        painter = rememberAsyncImagePainter(profileURL),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun setAstronautDetailsScreen(astronaut: AstronautDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = astronaut.name, style = MaterialTheme.typography.headlineLarge
        )
        TitleDescriptionItem(title = "Nationality", description = astronaut.nationality)
        if (astronaut.dateOfBirth.isNotBlank()) TitleDescriptionItem(
            title = "Date Of Birth", description = astronaut.dateOfBirth
        )
        if (astronaut.dateOfDeath.isNotBlank()) TitleDescriptionItem(
            title = "Date of Death", description = astronaut.dateOfDeath
        )
        TitleDescriptionItem(title = "First Flight", description = astronaut.firstFlight)
        TitleDescriptionItem(title = "Last Flight", description = astronaut.lastFlight)
        if (astronaut.bio.isNotBlank()) TitleDescriptionItem(
            title = "Bio", description = astronaut.bio
        )
        if (astronaut.flights.isNotEmpty()) {
            FlightList(astronaut.flights)
        }

        if (astronaut.agency.name.isNotEmpty()) {
            TitleDescriptionItem(title = "Agency", description = astronaut.agency.name)
        }
    }
}

@Composable
private fun FlightList(flights: List<Flight>) {
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = "Flight", style = MaterialTheme.typography.titleLarge, fontStyle = FontStyle.Italic
    )
    Spacer(modifier = Modifier.height(2.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(flights) {
            FlightItem(flightName = it.name)
        }
    }
    Spacer(modifier = Modifier.height(2.dp))
    Divider()
}