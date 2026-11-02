package com.myapp.pokedex.domain.repository

import com.myapp.pokedex.domain.model.Pokemon
import com.myapp.pokedex.domain.model.PokemonDetail
interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>
    suspend fun getPokemonDetail(id: Int): PokemonDetail
}