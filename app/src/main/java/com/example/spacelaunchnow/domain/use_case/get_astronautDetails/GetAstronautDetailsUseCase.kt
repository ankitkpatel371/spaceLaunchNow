package com.example.spacelaunchnow.domain.use_case.get_astronautDetails

import com.example.spacelaunchnow.common.Resource
import com.example.spacelaunchnow.data.remote.dto.toAstronautDetails
import com.example.spacelaunchnow.domain.model.AstronautDetails
import com.example.spacelaunchnow.domain.repository.AstronautRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

open class GetAstronautDetailsUseCase @Inject constructor(
    private val repository: AstronautRepository
) {
    open operator fun invoke(astronautID: Int): Flow<Resource<AstronautDetails>> = flow {
        try {
            emit(Resource.Loading())
            val astronautsDto = repository.getAstronautDetails(astronautID)
            emit(Resource.Success(astronautsDto.toAstronautDetails()))
        } catch (httpException: HttpException) {
            emit(Resource.Error("Server Error Occurred"))
        } catch (iOException: IOException) {
            emit(Resource.Error("Can't connect to server. Please check your internet connection."))
        }
    }
}