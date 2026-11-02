package com.myapp.pokedex.domain.usecase

import com.myapp.pokedex.domain.model.Pokemon
import com.myapp.pokedex.domain.model.PokemonDetail
import com.myapp.pokedex.domain.repository.PokemonRepository

class GetPokemonListUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0): List<Pokemon> =
        repository.getPokemonList(limit, offset)
}
class GetPokemonDetailUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(id: Int): PokemonDetail = repository.getPokemonDetail(id)
}