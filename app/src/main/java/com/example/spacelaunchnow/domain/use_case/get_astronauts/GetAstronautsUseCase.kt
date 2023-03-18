package com.example.spacelaunchnow.domain.use_case.get_astronauts

import com.example.spacelaunchnow.common.Resource
import com.example.spacelaunchnow.data.remote.dto.toAstronauts
import com.example.spacelaunchnow.domain.model.Astronaut
import com.example.spacelaunchnow.domain.repository.AstronautRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

open class GetAstronautsUseCase @Inject constructor(
    private val repository: AstronautRepository
) {
    open operator fun invoke(): Flow<Resource<List<Astronaut>>> = flow {
        try {
            emit(Resource.Loading())
            val astronautsDto = repository.getAstronautsList()
            emit(Resource.Success(astronautsDto.toAstronauts().astronauts))
        } catch (httpException: HttpException) {
            emit(Resource.Error("Server Error Occurred"))
        } catch (iOException: IOException) {
            emit(Resource.Error("Can't connect to server. Please check your internet connection."))
        }
    }
}