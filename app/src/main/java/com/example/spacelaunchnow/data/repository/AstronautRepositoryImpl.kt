package com.example.spacelaunchnow.data.repository

import com.example.spacelaunchnow.data.remote.SpaceLaunchAPI
import com.example.spacelaunchnow.data.remote.dto.AstronautDetailsDto
import com.example.spacelaunchnow.data.remote.dto.AstronautsDto
import com.example.spacelaunchnow.domain.repository.AstronautRepository
import javax.inject.Inject

class AstronautRepositoryImpl @Inject constructor(
    private val spaceLaunchAPI: SpaceLaunchAPI
) : AstronautRepository {
    override suspend fun getAstronautsList(): AstronautsDto {
        return spaceLaunchAPI.getAstronauts()
    }

    override suspend fun getAstronautDetails(astronautID: Int): AstronautDetailsDto {
        return spaceLaunchAPI.getAstronautDetails(astronautID)
    }
}