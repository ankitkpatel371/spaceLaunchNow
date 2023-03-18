package com.example.spacelaunchnow.data.remote

import com.example.spacelaunchnow.data.remote.dto.AstronautDetailsDto
import com.example.spacelaunchnow.data.remote.dto.AstronautsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceLaunchAPI {
    @GET("astronaut/")
    suspend fun getAstronauts(): AstronautsDto

    @GET("astronaut/{astronautId}/")
    suspend fun getAstronautDetails(@Path("astronautId") astronautId: Int): AstronautDetailsDto
}