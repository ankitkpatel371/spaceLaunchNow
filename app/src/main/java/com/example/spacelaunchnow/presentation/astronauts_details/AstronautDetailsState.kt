package com.example.spacelaunchnow.presentation.astronauts_details

import com.example.spacelaunchnow.domain.model.AstronautDetails

data class AstronautDetailsState(
    val isLoading: Boolean = false,
    val astronautDetails: AstronautDetails? = null,
    val error: String = ""
)