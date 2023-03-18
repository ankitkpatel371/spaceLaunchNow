package com.example.spacelaunchnow.presentation.astronauts_list

import app.cash.turbine.test
import com.example.spacelaunchnow.MainDispatcherRule
import com.example.spacelaunchnow.TestCoroutineRule
import com.example.spacelaunchnow.domain.model.Astronaut
import com.example.spacelaunchnow.domain.model.Astronauts
import com.example.spacelaunchnow.domain.repository.FakeAstronautRepository
import com.example.spacelaunchnow.domain.use_case.get_astronauts.FakeGetAstronautsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AstronautsListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: AstronautsListViewModel

    private val fakeAstronautRepository = FakeAstronautRepository()

    private val fakeGetAstronautsUseCase =
        FakeGetAstronautsUseCase(fakeAstronautRepository)

    private val astronautsList = Astronauts(
        mutableListOf(
            Astronaut(
                id = 1,
                name = "Hero",
                nationality = "American",
                dateOfBirth = "1/1/1990",
                dateOfDeath = "1/1/2022",
                firstFlight = "1/1/2008",
                lastFlight = "2/2/2012",
                profileImageThumbnail = "https://example.com/image.jpg"
            ),
            Astronaut(
                id = 2,
                name = "Astronaut",
                nationality = "American",
                dateOfBirth = "1/1/1990",
                dateOfDeath = "1/1/2022",
                firstFlight = "1/1/2008",
                lastFlight = "2/2/2012",
                profileImageThumbnail = "https://example.com/image.jpg"
            )
        )
    )


    @Before
    fun setup() {
        viewModel = AstronautsListViewModel(fakeGetAstronautsUseCase)
    }

    @Test
    fun stateIsLoadingWhenListIsSuccess() = runTest {
        viewModel.getAstronauts()
        viewModel.state.test {
            Assert.assertEquals(
                AstronautsState(isLoading = true),
                awaitItem(),
            )
            Assert.assertEquals(
                AstronautsState(astronautList = astronautsList.astronauts),
                awaitItem(),
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun stateIsLoadingWhenDetailsIsFail() = runTest {
        viewModel.getAstronauts()
        fakeGetAstronautsUseCase.setShouldReturnError(true)
        viewModel.state.test {
            Assert.assertEquals(
                AstronautsState(isLoading = true),
                awaitItem(),
            )
            Assert.assertEquals(
                AstronautsState(error = "Failed to fetch astronaut details"),
                awaitItem(),
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun shortAstronautsList() = runTest {
        viewModel.getAstronauts()
        viewModel.state.test {
            Assert.assertEquals(
                AstronautsState(isLoading = true),
                awaitItem(),
            )
            awaitItem().let {
                Assert.assertEquals(
                    AstronautsState(astronautList = astronautsList.astronauts),
                    it,
                )
                viewModel.shortAstronautsList()
                Assert.assertEquals(
                    AstronautsState(astronautList = astronautsList.astronauts.sortedBy { a ->
                        a.name
                    }),
                    viewModel.state.value,
                )
            }
            cancelAndIgnoreRemainingEvents()
        }
    }
}