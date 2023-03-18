package com.example.spacelaunchnow.domain.repository

import com.example.spacelaunchnow.data.remote.dto.AgencyDto
import com.example.spacelaunchnow.data.remote.dto.AstronautDetailsDto
import com.example.spacelaunchnow.data.remote.dto.AstronautDto
import com.example.spacelaunchnow.data.remote.dto.AstronautsDto
import com.example.spacelaunchnow.data.remote.dto.FlightDto

class FakeAstronautRepository : AstronautRepository {

    private val astronautsDto = AstronautsDto(
        mutableListOf(
            AstronautDto(
                bio = "This is Fake Bio",
                date_of_birth = "1/1/1990",
                date_of_death = "1/1/2022",
                first_flight = "1/1/2008",
                id = 1,
                instagram = null,
                last_flight = "2/2/2012",
                name = "Hero",
                nationality = "American",
                profile_image_thumbnail = "https://example.com/image.jpg",
                twitter = null,
                url = null,
                wiki = null
            ),
            AstronautDto(
                bio = "This is Fake Bio",
                date_of_birth = "1/1/1990",
                date_of_death = "1/1/2022",
                first_flight = "1/1/2008",
                id = 2,
                instagram = null,
                last_flight = "2/2/2012",
                name = "Astronaut",
                nationality = "American",
                profile_image_thumbnail = "https://example.com/image.jpg",
                twitter = null,
                url = null,
                wiki = null
            )
        )
    )

    val astronautDetailsDto = AstronautDetailsDto(
        agency = AgencyDto(id = 1, name = "agency", type = "type", url = "url"),
        bio = "This is Fake Bio",
        date_of_birth = "1/1/1990",
        date_of_death = "2/2/2022",
        first_flight = "1/1/2020",
        flights = listOf(FlightDto(name = "ABC", id = "1", url = "url")),
        id = 1,
        instagram = null,
        last_flight = "",
        name = "John Doe",
        nationality = "American",
        profile_image = "https://example.com/image.jpg",
        profile_image_thumbnail = "https://example.com/image.jpg",
        twitter = null,
        url = null,
        wiki = "https://example.com/wiki"
    )

    override suspend fun getAstronautsList(): AstronautsDto {
        return astronautsDto
    }

    override suspend fun getAstronautDetails(astronautID: Int): AstronautDetailsDto {
        return astronautDetailsDto
    }
}