package com.myapp.pokedex.data.remote.dto

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypesSlotDto>
)
data class TypesSlotDto(val type: TypeDto)
data class TypeDto(val name: String)