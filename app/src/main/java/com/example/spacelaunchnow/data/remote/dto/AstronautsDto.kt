package com.example.spacelaunchnow.data.remote.dto

import com.example.spacelaunchnow.domain.model.Astronauts
import com.google.gson.annotations.SerializedName

data class AstronautsDto(
    @SerializedName("results")
    val astronautsList: List<AstronautDto>
)

fun AstronautsDto.toAstronauts(): Astronauts = Astronauts(astronauts = astronautsList.map {
    it.toAstronaut()
})

