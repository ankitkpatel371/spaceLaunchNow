package com.example.spacelaunchnow.data.remote.dto

import com.example.spacelaunchnow.common.toDate
import com.example.spacelaunchnow.domain.model.AstronautDetails

data class AstronautDetailsDto(
    val agency: AgencyDto,
    val bio: String?,
    val date_of_birth: String?,
    val date_of_death: String?,
    val first_flight: String?,
    val flights: List<FlightDto>,
    val id: Int,
    val instagram: String?,
    val last_flight: String?,
    val name: String,
    val nationality: String,
    val profile_image: String,
    val profile_image_thumbnail: String,
    val twitter: String?,
    val url: String?,
    val wiki: String?
)

fun AstronautDetailsDto.toAstronautDetails() = AstronautDetails(
    agency = agency.toAgency(),
    bio = bio ?: "Not Available",
    dateOfBirth = date_of_birth?.toDate() ?: "-",
    dateOfDeath = date_of_death?.toDate() ?: "-",
    firstFlight = first_flight?.toDate() ?: "-",
    flights = flights.map { it.toFlight() },
    id = id,
    lastFlight = last_flight?.toDate() ?: "-",
    name = name,
    nationality = nationality,
    profileImage = profile_image,
    wiki = wiki ?: ""
)
