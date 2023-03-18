package com.example.spacelaunchnow.presentation.astronauts_list

import com.example.spacelaunchnow.domain.model.Astronaut

data class AstronautsState (
    val isLoading: Boolean = false,
    val astronautList: List<Astronaut> = emptyList<Astronaut>(),
    val error: String = ""
)