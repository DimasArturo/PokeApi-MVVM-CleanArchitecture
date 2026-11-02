package com.myapp.pokedex.data.repository

import com.myapp.pokedex.data.mapper.toDomain
import com.myapp.pokedex.data.remote.api.PokeApiService
import com.myapp.pokedex.domain.model.Pokemon
import com.myapp.pokedex.domain.model.PokemonDetail
import com.myapp.pokedex.domain.repository.PokemonRepository

class PokemonRepositoryImpl (private val api: PokeApiService) : PokemonRepository{
    override suspend fun getPokemonList (limit: Int, offset: Int ): List<Pokemon> =
        api.getPokemonList(limit, offset).results.map { it.toDomain() }
    override suspend fun getPokemonDetail(id: Int): PokemonDetail =
        api.getPokemonDetail(id).toDomain()
}
