package com.example.spacelaunchnow.presentation.astronauts_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacelaunchnow.common.Resource
import com.example.spacelaunchnow.domain.use_case.get_astronauts.GetAstronautsUseCase
import com.example.spacelaunchnow.presentation.astronauts_details.AstronautDetailsState
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
class AstronautsListViewModel @Inject constructor(
    private val getAstronautsUseCase: GetAstronautsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AstronautsState())
    val state: StateFlow<AstronautsState> = _state.asStateFlow()

    init {
        getAstronauts()
    }

    fun getAstronauts() {
        getAstronautsUseCase().map { result ->
            when (result) {
                is Resource.Success -> {
                    _state.emit(
                        AstronautsState(astronautList = result.data ?: emptyList())
                    )
                }

                is Resource.Error -> {
                    _state.emit(AstronautsState(error = result.message ?: "Don't know what happen"))
                }

                is Resource.Loading -> {
                    _state.emit(AstronautsState(isLoading = true))
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AstronautsState(isLoading = true),
        ).launchIn(viewModelScope)
    }

    fun shortAstronautsList() {
        _state.value =
            AstronautsState(astronautList = _state.value.astronautList.sortedBy { it.name })
    }
}