package com.example.spacelaunchnow.domain.use_case.get_astronauts

import com.example.spacelaunchnow.common.Resource
import com.example.spacelaunchnow.domain.model.Astronaut
import com.example.spacelaunchnow.domain.repository.FakeAstronautRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetAstronautsUseCase(fakeAstronautRepository: FakeAstronautRepository) :
    GetAstronautsUseCase(fakeAstronautRepository) {

    private var shouldReturnError = false

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override fun invoke(): Flow<Resource<List<Astronaut>>> = flow {
        emit(Resource.Loading())
        delay(1000)
        if (shouldReturnError) {
            emit(Resource.Error("Failed to fetch astronaut list"))
        } else {
            super.invoke().collect {
                emit(it)
            }
        }
    }
}