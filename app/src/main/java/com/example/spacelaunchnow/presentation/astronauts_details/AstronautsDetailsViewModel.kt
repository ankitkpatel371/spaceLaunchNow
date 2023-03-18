package com.example.spacelaunchnow.presentation.astronauts_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacelaunchnow.common.Constants.ASTRONAUTS_ID
import com.example.spacelaunchnow.common.Resource
import com.example.spacelaunchnow.domain.use_case.get_astronautDetails.GetAstronautDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AstronautsDetailsViewModel @Inject constructor(
    private val getAstronautDetailsUseCase: GetAstronautDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AstronautDetailsState())
    val state: StateFlow<AstronautDetailsState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>(ASTRONAUTS_ID)?.let {
            getAstronautDetails(it)
        }
    }

    fun getAstronautDetails(astronautsId: Int) {
        getAstronautDetailsUseCase.invoke(astronautsId).map {
            when (it) {
                is Resource.Success -> {
                    _state.emit(AstronautDetailsState(astronautDetails = it.data))
                }

                is Resource.Loading -> {
                    _state.emit(AstronautDetailsState(isLoading = true))
                }

                is Resource.Error -> {
                    _state.emit(
                        AstronautDetailsState(
                            error = it.message ?: "Don't know what happen"
                        )
                    )
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AstronautDetailsState(isLoading = true),
        ).launchIn(viewModelScope)
    }
}