package com.example.spacelaunchnow.presentation.astronauts_details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.spacelaunchnow.MainDispatcherRule
import com.example.spacelaunchnow.TestCoroutineRule
import com.example.spacelaunchnow.common.Constants.ASTRONAUTS_ID
import com.example.spacelaunchnow.domain.model.Agency
import com.example.spacelaunchnow.domain.model.AstronautDetails
import com.example.spacelaunchnow.domain.model.Flight
import com.example.spacelaunchnow.domain.repository.FakeAstronautRepository
import com.example.spacelaunchnow.domain.use_case.get_astronautDetails.FakeGetAstronautDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AstronautsDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: AstronautsDetailsViewModel

    lateinit var savedStateHandle: SavedStateHandle


    private lateinit var fakeAstronautRepository: FakeAstronautRepository

    private lateinit var fakeGetAstronautDetailsUseCase:
            FakeGetAstronautDetailsUseCase


    val astronautDetails = AstronautDetails(
        agency = Agency(id = 1, name = "agency"),
        bio = "This is Fake Bio",
        dateOfBirth = "1/1/1990",
        dateOfDeath = "2/2/2022",
        firstFlight = "1/1/2020",
        lastFlight = "",
        flights = listOf(Flight("ABC", "1")),
        id = 1,
        name = "John Doe",
        nationality = "American",
        profileImage = "https://example.com/image.jpg",
        wiki = "https://example.com/wiki"
    )


    @Before
    fun setup() {
        fakeAstronautRepository = FakeAstronautRepository()
        fakeGetAstronautDetailsUseCase =
            FakeGetAstronautDetailsUseCase(fakeAstronautRepository)
        savedStateHandle = SavedStateHandle()
        savedStateHandle[ASTRONAUTS_ID] = 1
        viewModel = AstronautsDetailsViewModel(fakeGetAstronautDetailsUseCase, savedStateHandle)
    }

    @Test
    fun stateIsLoadingWhenDetailsIsSuccess() = runTest {
        viewModel.getAstronautDetails(1)
        viewModel.state.test {
            assertEquals(
                AstronautDetailsState(isLoading = true),
                awaitItem(),
            )
            assertEquals(
                AstronautDetailsState(astronautDetails = astronautDetails),
                awaitItem(),
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun stateIsLoadingWhenDetailsIsFail() = runTest {
        viewModel.getAstronautDetails(1)
        fakeGetAstronautDetailsUseCase.setShouldReturnError(true)
        viewModel.state.test {
            assertEquals(
                AstronautDetailsState(isLoading = true),
                awaitItem(),
            )
            assertEquals(
                AstronautDetailsState(error = "Failed to fetch astronaut details"),
                awaitItem(),
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun stateIsLoadingWhenListIsWithIOException() = runTest {
        viewModel.getAstronautDetails(1)
        fakeAstronautRepository.shouldReturnIOException(true)
        viewModel.state.test {
            assertEquals(
                AstronautDetailsState(isLoading = true),
                awaitItem(),
            )
            assertEquals(
                AstronautDetailsState(error = "Can't connect to server. Please check your internet connection."),
                awaitItem(),
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun stateIsLoadingWhenListIsWithHTTPException() = runTest {
        viewModel.getAstronautDetails(1)
        fakeAstronautRepository.shouldReturnHTTPException(true)
        viewModel.state.test {
            assertEquals(
                AstronautDetailsState(isLoading = true),
                awaitItem(),
            )
            assertEquals(
                AstronautDetailsState(error = "Server Error Occurred"),
                awaitItem(),
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}

