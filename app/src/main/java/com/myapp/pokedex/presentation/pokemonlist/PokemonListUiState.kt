package com.myapp.pokedex.presentation.pokemonlist

import com.myapp.pokedex.domain.model.Pokemon
import retrofit2.http.Query

data class PokemonListUiState(
    val isLoading: Boolean = false,
    val pokemons: List<Pokemon> = emptyList(),
    val error: String? = null,
    val query: String = ""
){
    val filteredPokemons: List<Pokemon>
        get() =  if (query.isBlank()) pokemons
    else pokemons.filter { it.name.contains(query.trim(), ignoreCase = true) }
}