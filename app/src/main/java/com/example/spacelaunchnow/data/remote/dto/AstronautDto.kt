package com.example.spacelaunchnow.data.remote.dto

import com.example.spacelaunchnow.common.toDate
import com.example.spacelaunchnow.domain.model.Astronaut

data class AstronautDto(
    val bio: String?,
    val date_of_birth: String?,
    val date_of_death: String?,
    val first_flight: String?,
    val id: Int,
    val instagram: String?,
    val last_flight: String?,
    val name: String,
    val nationality: String,
    val profile_image_thumbnail: String?,
    val twitter: String?,
    val url: String?,
    val wiki: String?
)

fun AstronautDto.toAstronaut(): Astronaut = Astronaut(
    dateOfBirth = date_of_birth?.toDate() ?: "",
    dateOfDeath = date_of_death?.toDate() ?: "",
    firstFlight = first_flight?.toDate() ?: "Not available",
    id = id,
    lastFlight = last_flight?.toDate() ?: "Not available",
    name = name,
    nationality = nationality,
    profileImageThumbnail = profile_image_thumbnail ?: ""
)