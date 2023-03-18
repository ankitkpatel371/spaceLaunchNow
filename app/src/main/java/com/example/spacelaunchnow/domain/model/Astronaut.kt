package com.example.spacelaunchnow.domain.model

data class Astronaut(
    val dateOfBirth: String,
    val dateOfDeath: String,
    val firstFlight: String,
    val id: Int,
    val lastFlight: String,
    val name: String,
    val nationality: String,
    val profileImageThumbnail: String,
)
