package com.example.spacelaunchnow.domain.model

data class AstronautDetails(
    val agency: Agency,
    val bio: String,
    val dateOfBirth: String,
    val dateOfDeath: String,
    val firstFlight: String,
    val flights: List<Flight>,
    val id: Int,
    val lastFlight: String,
    val name: String,
    val nationality: String,
    val profileImage: String,
    val wiki: String
)