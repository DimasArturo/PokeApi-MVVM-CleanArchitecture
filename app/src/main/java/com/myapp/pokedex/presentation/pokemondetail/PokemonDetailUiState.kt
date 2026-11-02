package com.myapp.pokedex.presentation.pokemondetail

import com.myapp.pokedex.domain.model.PokemonDetail

data class PokemonDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetail? = null,
    val error: String? = null
)