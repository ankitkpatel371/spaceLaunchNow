package com.example.spacelaunchnow.domain.repository

import com.example.spacelaunchnow.data.remote.dto.AgencyDto
import com.example.spacelaunchnow.data.remote.dto.AstronautDetailsDto
import com.example.spacelaunchnow.data.remote.dto.AstronautDto
import com.example.spacelaunchnow.data.remote.dto.AstronautsDto
import com.example.spacelaunchnow.data.remote.dto.FlightDto
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class FakeAstronautRepository : AstronautRepository {

    private val astronautsDto = AstronautsDto(
        mutableListOf(
            AstronautDto(
                bio = "This is Fake Bio",
                date_of_birth = "1974-11-13",
                date_of_death = null,
                first_flight = "2014-11-24T07:01:14+10:00",
                id = 1,
                instagram = null,
                last_flight = "2022-04-27T18:52:55+11:00",
                name = "Hero",
                nationality = "American",
                profile_image_thumbnail = "https://example.com/image.jpg",
                twitter = null,
                url = null,
                wiki = null
            ),
            AstronautDto(
                bio = "This is Fake Bio",
                date_of_birth = "1974-11-13",
                date_of_death = null,
                first_flight = "2014-11-24T07:01:14+10:00",
                id = 2,
                instagram = null,
                last_flight = "2022-04-27T18:52:55+11:00",
                name = "Astronaut",
                nationality = "American",
                profile_image_thumbnail = "https://example.com/image.jpg",
                twitter = null,
                url = null,
                wiki = null
            )
        )
    )

    private val httpErrorResponse: Response<Error> = Response.error(
        403,
        "Not Found"
            .toResponseBody("application/json".toMediaTypeOrNull())
    )

    private var isIOException = false
    private var isHTTPException = false

    private val astronautDetailsDto = AstronautDetailsDto(
        agency = AgencyDto(id = 1, name = "agency", type = "type", url = "url"),
        bio = "This is Fake Bio",
        date_of_birth = "1974-11-13",
        date_of_death = null,
        first_flight = "2014-11-24T07:01:14+10:00",
        flights = listOf(FlightDto(name = "ABC", id = "1", url = "url")),
        id = 1,
        instagram = null,
        last_flight = "2022-04-27T18:52:55+11:00",
        name = "John Doe",
        nationality = "American",
        profile_image = "https://example.com/image.jpg",
        profile_image_thumbnail = "https://example.com/image.jpg",
        twitter = null,
        url = null,
        wiki = "https://example.com/wiki"
    )

    override suspend fun getAstronautsList(): AstronautsDto {
        if (isIOException) {
            throw IOException()
        }
        if (isHTTPException) {
            throw HttpException(httpErrorResponse)
        }
        return astronautsDto
    }

    override suspend fun getAstronautDetails(astronautID: Int): AstronautDetailsDto {
        if (isIOException) {
            throw IOException()
        }
        if (isHTTPException) {
            throw HttpException(httpErrorResponse)
        }
        return astronautDetailsDto
    }

    fun shouldReturnIOException(_isIOException: Boolean) {
        isIOException = _isIOException
    }

    fun shouldReturnHTTPException(_isHTTPException: Boolean) {
        isHTTPException = _isHTTPException
    }
}