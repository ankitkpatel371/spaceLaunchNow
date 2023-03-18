package com.example.spacelaunchnow.data.remote.dto

import com.example.spacelaunchnow.domain.model.Agency

data class AgencyDto(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)

fun AgencyDto.toAgency(): Agency = Agency(
    id = id,
    name = name
)