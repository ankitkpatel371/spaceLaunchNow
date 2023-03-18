package com.example.spacelaunchnow.domain.repository

import com.example.spacelaunchnow.data.remote.dto.AstronautDetailsDto
import com.example.spacelaunchnow.data.remote.dto.AstronautsDto

interface AstronautRepository {
    suspend fun getAstronautsList(): AstronautsDto
    suspend fun getAstronautDetails(astronautID: Int): AstronautDetailsDto
}