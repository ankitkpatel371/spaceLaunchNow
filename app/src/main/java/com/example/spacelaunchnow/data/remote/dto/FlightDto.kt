package com.example.spacelaunchnow.data.remote.dto

import com.example.spacelaunchnow.domain.model.Flight

data class FlightDto(
    val name: String,
    val id: String,
    val url: String
)

fun FlightDto.toFlight(): Flight = Flight(
    name = name,
    id = id
)

