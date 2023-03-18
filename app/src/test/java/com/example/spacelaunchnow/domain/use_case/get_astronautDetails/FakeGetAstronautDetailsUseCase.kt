package com.example.spacelaunchnow.domain.use_case.get_astronautDetails

import com.example.spacelaunchnow.common.Resource
import com.example.spacelaunchnow.data.remote.dto.toAstronautDetails
import com.example.spacelaunchnow.domain.model.AstronautDetails
import com.example.spacelaunchnow.domain.repository.FakeAstronautRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetAstronautDetailsUseCase(var fakeAstronautRepository: FakeAstronautRepository) :
    GetAstronautDetailsUseCase(fakeAstronautRepository) {

    private var shouldReturnError = false

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override fun invoke(astronautID: Int): Flow<Resource<AstronautDetails>> = flow {
        emit(Resource.Loading())
        delay(1000)
        if (shouldReturnError) {
            emit(Resource.Error("Failed to fetch astronaut details"))
        } else {
            emit(
                Resource.Success(
                    fakeAstronautRepository.getAstronautDetails(1).toAstronautDetails()
                )
            )
        }
    }
}