package com.myapp.pokedex.data.mapper

import com.myapp.pokedex.data.remote.dto.PokemonDetailDto
import com.myapp.pokedex.data.remote.dto.PokemonItemDto
import com.myapp.pokedex.domain.model.Pokemon
import com.myapp.pokedex.domain.model.PokemonDetail

fun PokemonItemDto.toDomain(): Pokemon {
    val id = url.trimEnd('/').substringAfterLast('/').toInt()
    return Pokemon(id, name.replaceFirstChar { it.uppercase() }, spriteUrl(id))
}

fun PokemonDetailDto.toDomain() = PokemonDetail(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    imageUrl = spriteUrl(id),
    height = height,
    weight = weight,
    types = types.map { it.type.name }
)

private fun spriteUrl(id: Int) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"